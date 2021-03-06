/**
 * Project Name:mk-cache <br>
 * Package Name:com.suns.service.impl <br>
 *
 * @author mk <br>
 * Date:2019-1-11 11:04 <br>
 */

package com.suns.service.impl;

import com.suns.dao.CitiesDao;
import com.suns.dao.ProvincesDao;
import com.suns.entity.Provinces;
import com.suns.service.ProvincesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: ProvincesServiceImpl <br>
 * Description: 原始实现,不加缓存 <br>
 * @author mk
 * @Date 2019-1-11 11:04 <br>
 * @version
 */
//@Service("provincesService")
public class ProvincesServiceImpl implements ProvincesService {

    @Autowired
    private ProvincesDao provincesDao;
    @Autowired
    private CitiesDao citiesDao;

    public List<Provinces> list(){
        return provincesDao.list();
    }

    @Override
    public Provinces detail(String provinceid) {
        Provinces provinces = null;

        System.out.println("数据库中得到数据--------"+System.currentTimeMillis());
        provinces = provincesDao.detail(provinceid);
        if (null != provinces){
            provinces.setCities(citiesDao.list(provinceid));
        }

        return provinces;
    }

    @Override
    public Provinces update(Provinces entity) {
        provincesDao.update(entity);
        return entity;
    }

    @Override
    public Provinces add(Provinces entity) {
        provincesDao.insert(entity);
        return entity;
    }

    @Override
    public void delete(String provinceid) {
        provincesDao.delete(provinceid);
    }
}
