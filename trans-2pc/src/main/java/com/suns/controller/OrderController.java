/**
 * Project Name:mk-cache <br>
 * Package Name:com.suns.controller <br>
 *
 * @author mk <br>
 * Date:2019-1-15 16:57 <br>
 */

package com.suns.controller;

import com.suns.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * ClassName: OrderController <br>
 * Description:  <br>
 * @author mk
 * @Date 2019-1-15 16:57 <br>
 * @version
 */
@Api(value = "2pc演示",description = "2pc演示")
@RestController
@RequestMapping("/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "doOrder")
    @RequestMapping(value = "doOrder",method = RequestMethod.GET)
    public String doOrder(@RequestParam("idcard")String idcard){
        try{
            String uuid = UUID.randomUUID().toString();
            return orderService.doOrder(uuid,idcard);
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
