/**
 * Project Name:mk-cache <br>
 * Package Name:com.suns.swagger <br>
 *
 * @author mk <br>
 * Date:2019-1-16 14:05 <br>
 */

package com.suns.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * ClassName: SwaggerConfig <br>
 * Description:  <br>
 * @author mk
 * @Date 2019-1-16 14:05 <br>
 * @version
 */
@EnableWebMvc
@EnableSwagger2 //启用Swagger2
@ComponentScan(basePackages = { "com.suns" }) //指定Swagger扫面的包
@Configuration //让Spring来加载该类配置
public class SwaggerConfig {

    @Bean
    public Docket customDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.suns"))
                .build();
    }


    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("tcc-one").version("1.0").build();
    }
}
