package com.suns.dao;

import com.suns.entity.Cities;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

public interface CitiesDao extends Serializable{

    int deleteById(String[] ids);

    int updateByEntity(final Cities entity);

    int insert(final Cities entity);

    List<Cities> list(@Param("provinceid") String provinceid);



}