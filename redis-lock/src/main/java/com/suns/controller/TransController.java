/**
 * Project Name:mk-cache <br>
 * Package Name:com.suns.controller <br>
 *
 * @author mk <br>
 * Date:2019-1-15 15:20 <br>
 */

package com.suns.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ClassName: TransController <br>
 * Description:  <br>
 * @author mk
 * @Date 2019-1-15 15:20 <br>
 * @version
 */
@Api(value = "事务示例",description = "事务示例")
@RestController
public class TransController {

    @Resource
    private JdbcTemplate jdbcTemplate;// ---- 会不会有变化

    // 配置事务管理操作类
    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;


    @ApiOperation(value = "spring编程式事务")
    @RequestMapping(value = "/spring/trans/{id}",method = RequestMethod.GET)
    public Long spring(@RequestParam("id") final int id){
        final String sql = "insert into db_lock (id) values (?)";
        // 通过transactionTemplate进行事务的管理
        transactionTemplate.execute(new TransactionCallbackWithoutResult(){

            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                int i = id;
                jdbcTemplate.update(sql,i++);
                jdbcTemplate.update(sql,i++);
                jdbcTemplate.update(sql, i++);
            }
        });
        return 1L;
    }

    @ApiOperation(value = "java编程式事务")
    @RequestMapping(value = "/trans/{id}",method = RequestMethod.GET)
    public Long trans(@RequestParam("id") final int id){
        final String sql = "insert into db_lock (id) values (?)";
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(new DefaultTransactionDefinition());
        try{
            int i = id;
            jdbcTemplate.update(sql,i++);
            jdbcTemplate.update(sql,i++);
            jdbcTemplate.update(sql, i++);

            dataSourceTransactionManager.commit(transactionStatus);
        }catch (Exception e){
            e.printStackTrace();
            dataSourceTransactionManager.rollback(transactionStatus);
        }


        //transactionManager2

//        jdbcTemplate.update(sql,i++);
//        jdbcTemplate.update(sql,i++);
//        jdbcTemplate.update(sql,i++);

//        dataSourceTransactionManager.commit(transactionStatus);
//        transactionManager2.commit(transactionStatus);

        return 1L;
    }

}
