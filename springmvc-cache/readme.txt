spring cache

redis与spring cache整合

1.增加依赖
    <!-- Start: redis -->
    <dependency>
        <groupId>org.springframework.data</groupId>
        <artifactId>spring-data-redis</artifactId>
        <version>1.6.0.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>redis.clients</groupId>
        <artifactId>jedis</artifactId>
        <version>2.9.0</version>
    </dependency>

    <!-- Spring Fox Swagger 2 -->
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger2</artifactId>
        <version>${springfox.version}</version>
    </dependency>
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger-ui</artifactId>
        <version>${springfox.version}</version>
    </dependency>
    <dependency>
        <groupId>org.webjars</groupId>
        <artifactId>bootstrap</artifactId>
        <version>3.3.5</version>
    </dependency>

2.增加配置
    详见spring-redis.xml
3.使用
    3.1使用redisTemplate
        详见：ProvincesServiceImpl
    3.2使用Spring cache
        详见：ProvincesServiceImpl2

4. 入口==》通过swagger访问：http://localhost:8080/swagger-ui.html