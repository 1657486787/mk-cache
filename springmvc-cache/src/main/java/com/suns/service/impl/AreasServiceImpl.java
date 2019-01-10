package com.suns.service.impl;

import com.suns.dao.AreasDao;
import com.suns.entity.Areas;
import com.suns.service.AreasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("areasService")
public class AreasServiceImpl implements AreasService {
    
    private static final Logger logger = LoggerFactory.getLogger(AreasServiceImpl.class);

    @Autowired
    private AreasDao areasDao;


    public int delete(String[] ids){
        return areasDao.deleteById(ids);
    }


    public int update(final Areas entity){
        return areasDao.updateByEntity(entity);
    }


    public int add(final Areas entity){
        return areasDao.insert(entity);
    }


    public List<Areas> list(){
        return areasDao.list();
    }



}