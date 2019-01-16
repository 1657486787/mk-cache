/**
 * Project Name:mk-cache <br>
 * Package Name:com.suns.service <br>
 *
 * @author mk <br>
 * Date:2019-1-15 17:23 <br>
 */

package com.suns.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClassName: OrderService <br>
 * Description:  <br>
 * @author mk
 * @Date 2019-1-15 17:23 <br>
 * @version
 */
@Service
public class OrderService {

    @Autowired
    private JdbcTemplate ghJdbcTemplate;

    @Autowired
    private JdbcTemplate nhJdbcTemplate;

    @Transactional
    public String doOrder(String busId,String idcard){
        System.out.println("start...");

        String sql = "UPDATE tcc_fly_order SET bus_id=?,STATUS = 1,idcard=?,remark=? WHERE STATUS = 0 LIMIT 1";

        //锁定订单
        int ret = ghJdbcTemplate.update(sql,busId,idcard,"国航");
        if (ret != 1){
            throw new RuntimeException("国航下单失败，无票可订");
        }

        //锁定订单
        ret = nhJdbcTemplate.update(sql,busId,idcard,"南航");
        if (ret != 1){
            throw new RuntimeException("南航下单失败，无票可订");
        }

        System.out.println("end...");
        return "success";
    }
}
