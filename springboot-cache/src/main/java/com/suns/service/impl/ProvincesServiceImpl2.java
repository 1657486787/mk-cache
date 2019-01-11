package com.suns.service.impl;

import com.suns.dao.CitiesDao;
import com.suns.dao.ProvincesDao;
import com.suns.entity.Provinces;
import com.suns.service.ProvincesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description: spring cache 实现缓存 <br>
 * @author mk
 * @Date 2019-1-11 10:49 <br>
 * @Param
 * @return
 */
//@Service("provincesService")
@CacheConfig(cacheNames = com.suns.config.CacheConfig.CACHE_NAME) //通用配置
public class ProvincesServiceImpl2 implements ProvincesService {
    private static final Logger logger = LoggerFactory.getLogger(ProvincesServiceImpl2.class);
    @Autowired
    private ProvincesDao provincesDao;
    @Autowired
    private CitiesDao citiesDao;

    public List<Provinces> list(){
        return provincesDao.list();
    }

    /**
     * 查询
     * @param provinceid
     * @return
     */
    //    @Cacheable(value = "province",
    //    key = "#root.targetClass.simpleName+':'+#root.methodName+':'+#provinceid")
    //    value指定当前接口，要使用哪一个缓存器 --- 如果该缓存器不存在，则创建一个
    @Cacheable
    @Override
    public Provinces detail(String provinceid) {//一个接口方法，对应一个缓存器
        System.out.println("数据库中得到数据");
        Provinces provinces = provincesDao.detail(provinceid);
        if (null != provinces){
            provinces.setCities(citiesDao.list(provinceid));
        }
        return provinces;
    }

    /**
     * //新增一个方法---- 缓存的都是方法返回值
     * @param entity
     * @return
     */
    @CachePut(key = "#entity.provinceid")
    @Override
    public Provinces update(Provinces entity) {
        provincesDao.update(entity);
        return entity;
    }

    //组合配置
    @Caching(put = {
            @CachePut(key = "#entity.provinceid"),
            @CachePut(key = "#entity.provinceid")})
    public Provinces add(Provinces entity) {
        provincesDao.insert(entity);
        return entity;
    }

    @CacheEvict(key = "provinceid")
    @Override
    public void delete(String provinceid) {
        provincesDao.delete(provinceid);
    }


}