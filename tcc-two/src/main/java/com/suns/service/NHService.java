/**
 * Project Name:mk-cache <br>
 * Package Name:com.suns.service <br>
 * @author mk <br>
 * Date:2019-1-16 15:05 <br>
 */
 
package com.suns.service;

import org.mengyun.tcctransaction.api.Compensable;

/**
 * Name: NHService <br>
 * Description:  <br>
 * @author mk <br>
 * @Date 2019-1-16 15:05 <br>
 * @version
 */
public interface NHService {

    @Compensable
    public int doOrder(String busId,String idcard);
}
