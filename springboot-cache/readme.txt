spring cache

redis与spring cache整合

1.增加依赖
     <!-- redis -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>

    <!-- fastjson -->
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>fastjson</artifactId>
        <version>1.2.30</version>
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

2.初始化数据库脚本
    cache.sql

3.增加配置
    缓存配置CacheConfig
    数据库扫描配置@MapperScan("com.suns.dao")

4.使用
    3.1原始实现,不加缓存
        详见：ProvincesServiceImpl
    3.2使用redisTemplate
        详见：ProvincesServiceImpl1
    3.3使用Spring cache
        详见：ProvincesServiceImpl2
    3.4缓存雪崩解决方案
        出现原因：某一时刻，缓存失效，大量并发发起请求时，导致所有的请求都到数据库，数据库压力过大可能导致宕机
        解决办法：加锁
        详见：ProvincesServiceImpl3    detail方法
    3.5缓存穿透解决方案
        出现原因：恶意查询系统中不存在的数据，导致直接到数据库查询。比如黑客攻击
        解决办法：使用布隆过滤器
        详见：ProvincesServiceImpl4    detail方法

5. 入口==》通过swagger访问：http://localhost:8090/swagger-ui.html