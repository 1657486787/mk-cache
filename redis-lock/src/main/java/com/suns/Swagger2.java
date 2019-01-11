/**
 * Project Name:mk-cache <br>
 * Package Name:com.suns <br>
 *
 * @author mk <br>
 * Date:2019-1-11 15:01 <br>
 */

package com.suns;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * ClassName: Swagger2 <br>
 * Description:  <br>
 * @author mk
 * @Date 2019-1-11 15:01 <br>
 * @version
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
//                .host(swaggerHost)
                    .apiInfo(apiInfo())
//                .pathMapping("/")
//                .directModelSubstitute(Date.class,String.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.suns"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("redis-lock")
                .version("1.0")
                .build();
    }
}
