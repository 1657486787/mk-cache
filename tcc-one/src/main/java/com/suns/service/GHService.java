package com.suns.service;

import org.mengyun.tcctransaction.api.Compensable;

public interface GHService {
    @Compensable
    public int doOrder(String orderid, String idcard);
}