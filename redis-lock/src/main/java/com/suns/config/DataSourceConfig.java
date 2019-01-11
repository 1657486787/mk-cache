/**
 * Project Name:mk-cache <br>
 * Package Name:com.suns.config <br>
 *
 * @author mk <br>
 * Date:2019-1-11 15:16 <br>
 */

package com.suns.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * ClassName: DataSourceConfig <br>
 * Description:  <br>
 * @author mk
 * @Date 2019-1-11 15:16 <br>
 * @version
 */
@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();//c3p0
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://10.50.10.201:3306/cfb_esign?useUnicode=true&characterEncoding=utf8&autoReconnect=true&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull");
        dataSource.setUser("develop");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws PropertyVetoException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() throws PropertyVetoException {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public TransactionTemplate transactionTemplate(PlatformTransactionManager platformTransactionManager){
        return new TransactionTemplate(platformTransactionManager);
    }

}
