/**
 * Project Name:mk-cache <br>
 * Package Name:com.suns.service.impl <br>
 *
 * @author mk <br>
 * Date:2019-1-11 11:01 <br>
 */

package com.suns.service.impl;

import com.suns.config.CacheConfig;
import com.suns.dao.CitiesDao;
import com.suns.dao.ProvincesDao;
import com.suns.entity.Provinces;
import com.suns.service.ProvincesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName: ProvincesServiceImpl3 <br>
 * Description: 缓存雪崩解决方案 <br>
 * @author mk
 * @Date 2019-1-11 11:01 <br>
 * @version
 */
//@Service("provincesService")
public class ProvincesServiceImpl3 extends ProvincesServiceImpl implements ProvincesService {

    private static final Logger logger = LoggerFactory.getLogger(ProvincesServiceImpl3.class);

    @Autowired
    private CacheManager cacheManager;

    private ConcurrentHashMap<String,Lock> locks = new ConcurrentHashMap<>();


    public Provinces detail(String provinceid) {

        //1.从缓存中获取数据
        Cache.ValueWrapper valueWrapper = cacheManager.getCache(CacheConfig.CACHE_NAME).get(provinceid);
        if(null != valueWrapper){
            System.out.println("缓存中得到数据");
            return (Provinces) valueWrapper.get();
        }
        //2.加锁排队，阻塞式锁
        doLock(provinceid);
        try{
            //第二个线程进来了，一次只有一个线程，双重校验，不加也没关系，无非是多刷几次库
            valueWrapper = cacheManager.getCache(CacheConfig.CACHE_NAME).get(provinceid);
            if(null != valueWrapper){
                System.out.println("缓存中得到数据");
                return (Provinces) valueWrapper.get();
            }

            Provinces provinces = super.detail(provinceid);
            System.out.println("数据库中得到数据--------"+System.currentTimeMillis());

            //3.放入缓存
            if (null != provinces){
                cacheManager.getCache(CacheConfig.CACHE_NAME).put(provinceid,provinces);
            }
            return provinces;
        } catch (Exception e){
            e.printStackTrace();
        } finally{
            //4.解锁
            releaseLock(provinceid);
        }

        return null;
    }

    private void doLock(String lockId) {

        //provinceid有不同的值，参数多样化
        //provinceid相同的，加一个锁，---- 不是同一个key，不能用同一个锁

        ReentrantLock newLock = new ReentrantLock();//创建一个锁
        Lock oldLock = locks.putIfAbsent(lockId, newLock);//若已存在，则newLock直接丢弃
        if(null != oldLock){
            oldLock.lock();
        }else{
            newLock.lock();
        }
    }

    private void releaseLock(String lockId) {

        ReentrantLock oldLock = (ReentrantLock) locks.get(lockId);
        if(null != oldLock && oldLock.isHeldByCurrentThread()){
            oldLock.unlock();
        }
    }

}
