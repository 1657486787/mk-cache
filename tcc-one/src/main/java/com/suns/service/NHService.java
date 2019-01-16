package com.suns.service;

import org.mengyun.tcctransaction.api.Compensable;

public interface NHService {

    @Compensable
    public int doOrder(String orderid, String idcard);
}