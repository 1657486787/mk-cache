/**
 * Project Name:mk-cache <br>
 * Package Name:com.suns.controller <br>
 *
 * @author mk <br>
 * Date:2019-1-10 14:09 <br>
 */

package com.suns.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * ClassName: TestController <br>
 * Description:  <br>
 * @author mk
 * @Date 2019-1-10 14:09 <br>
 * @version
 */
@Api(value = "测试Controller",description = "测试Api")
@RestController
@RequestMapping("/test")
public class TestController {


    @ApiOperation("测试查询")
    @RequestMapping(value = "/query.do",method = RequestMethod.GET)
    public Object query(){
        return new Date().toString();
    }

}
