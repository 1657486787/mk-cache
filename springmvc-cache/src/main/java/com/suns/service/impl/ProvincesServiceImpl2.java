package com.suns.service.impl;

import com.suns.dao.CitiesDao;
import com.suns.dao.ProvincesDao;
import com.suns.entity.Provinces;
import com.suns.service.ProvincesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.Cacheable;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service("provincesService2")
public class ProvincesServiceImpl2 implements ProvincesService {
    private static final Logger logger = LoggerFactory.getLogger(ProvincesServiceImpl2.class);
    @Autowired
    private ProvincesDao provincesDao;
    @Autowired
    private CitiesDao citiesDao;

    public List<Provinces> list(){
        return provincesDao.list();
    }

    @Cacheable(value = "provinces2")
    @Override
    public Provinces detail(String provinceid) {
        System.out.println("数据库中得到数据");
        Provinces provinces = provincesDao.detail(provinceid);
        if (null != provinces){
            provinces.setCities(citiesDao.list(provinceid));
        }
        return provinces;
    }

    @CachePut(value = "provinces2",key = "#entity.provinceid")
    @Override
    public Provinces update(Provinces entity) {
        provincesDao.update(entity);
        return entity;
    }

    @CacheEvict(value = "provinces2",key = "#entity.provinceid")
    @Override
    public Provinces add(Provinces entity) {
        provincesDao.insert(entity);
        return entity;
    }

    @CacheEvict(value = "provinces2",key = "provinceid")
    @Override
    public void delete(String provinceid) {
        provincesDao.delete(provinceid);
    }


}