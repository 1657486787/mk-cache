/**
 * Project Name:mk-cache <br>
 * Package Name:com.suns.service.impl <br>
 *
 * @author mk <br>
 * Date:2019-1-11 11:33 <br>
 */

package com.suns.service.impl;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.suns.config.CacheConfig;
import com.suns.entity.Provinces;
import com.suns.service.ProvincesService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * ClassName: ProvincesServiceImpl4 <br>
 * Description: 缓存穿透解决方案 <br>
 * @author mk
 * @Date 2019-1-11 11:33 <br>
 * @version
 */
@Service("provincesService")
public class ProvincesServiceImpl4 extends ProvincesServiceImpl implements ProvincesService {

    private BloomFilter<String> bloomFilter =null; //等效成一个set集合

    /**
     * Description:  <br>
     *     //对象创建后，自动调用本方法
     *     //在bean初始化完成后，实例化bloomFilter,并加载数据
     * @author mk
     * @Date 2019-1-11 11:37 <br>
     * @Param
     * @return
     */
    @PostConstruct
    public void init(){
        List<Provinces> list = this.list();
        bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), list.size());
        for(Provinces provinces : list){
            bloomFilter.put(provinces.getProvinceid());
        }
    }

    @Cacheable(value = CacheConfig.CACHE_NAME)
    public Provinces detail(String provinceid) {
        //先判断布隆过滤器中是否存在该值，值存在才允许访问缓存和数据库
        if(!bloomFilter.mightContain(provinceid)){
            System.out.println("非法访问--------"+System.currentTimeMillis());
            return null;
        }
        Provinces provinces = super.detail(provinceid);
        return provinces;
    }


    @CachePut(value = CacheConfig.CACHE_NAME,key = "#entity.provinceid")
    public Provinces update(Provinces entity) {
        super.update(entity);
        return entity;
    }

    @CacheEvict(value = CacheConfig.CACHE_NAME,key = "#entity.provinceid")
    public Provinces add(Provinces entity) {
        super.add(entity);
        return entity;
    }

    @Override
    @CacheEvict(CacheConfig.CACHE_NAME)
    public void delete(String provinceid) {
        super.delete(provinceid);
    }
}
