package com.suns;

import com.suns.config.DataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableAutoConfiguration
@ComponentScan("com.suns")
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@Import({ DataSourceConfig.class })

//@SpringBootApplication
public class Trans2pcApplication {

    public static void main(String[] args) {
        SpringApplication.run(Trans2pcApplication.class, args);
    }

}

