package com.suns.service;


import com.suns.entity.Areas;

import java.util.List;

public interface AreasService {
    int delete(String[] ids);
    int update(final Areas entity);
    int add(final Areas entity);
    List<Areas> list();
}