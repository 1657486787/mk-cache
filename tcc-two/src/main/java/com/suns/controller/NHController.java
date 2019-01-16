/**
 * Project Name:mk-cache <br>
 * Package Name:com.suns.controller <br>
 *
 * @author mk <br>
 * Date:2019-1-16 15:04 <br>
 */

package com.suns.controller;

import com.suns.service.NHService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * ClassName: NHController <br>
 * Description:  <br>
 * @author mk
 * @Date 2019-1-16 15:04 <br>
 * @version
 */
@RestController
@RequestMapping("/order")
public class NHController {

    private static final Logger logger = LoggerFactory.getLogger(NHController.class);

    @Autowired
    private NHService nhService;

    @ApiOperation(value="南航下单")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String doOrder(@ApiParam(value = "身份证号", required = true) @RequestParam("idcard") String idcard){
        try {
            String busId = UUID.randomUUID().toString();
            nhService.doOrder(busId,idcard);
            return "doOrder successfully";
        } catch (Exception e) {
            logger.error("下单失败",e);
            return e.getMessage();
        }
    }
}
