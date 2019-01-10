package com.suns.service;


import com.suns.entity.Cities;

import java.util.List;

public interface CitiesService {
    public int update(final Cities entity);
    public int add(final Cities entity);
    public List<Cities> list(String provinceid);
}