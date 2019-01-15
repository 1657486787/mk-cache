分布式锁机制
    实现方式有mysql/redis/zookeeper
    mysql:简单，性能低
    redis:性能高，可能出现死锁
    zookeeper:性能高

1.增加依赖
            <!-- web依赖 -->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
            </dependency>

            <!-- 数据库配置 -->
            <dependency>
                <groupId>com.mchange</groupId>
                <artifactId>c3p0</artifactId>
                <version>0.9.5-pre10</version>
            </dependency>
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>5.1.34</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-jdbc</artifactId>
                <version>${springframework.version}</version>
            </dependency>

            <!-- config jedis data and client jar -->
            <dependency>
                <groupId>org.springframework.data</groupId>
                <artifactId>spring-data-redis</artifactId>
                <version>1.7.1.RELEASE</version>
            </dependency>

            <dependency>
                <groupId>redis.clients</groupId>
                <artifactId>jedis</artifactId>
                <version>2.8.1</version>
            </dependency>

            <dependency>
                <groupId>com.mchange</groupId>
                <artifactId>c3p0</artifactId>
                <version>0.9.5-pre10</version>
            </dependency>

            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>fastjson</artifactId>
                <version>1.2.47</version>
            </dependency>

            <!--使用swagger2-->
            <dependency>
                <groupId>io.springfox</groupId>
                <artifactId>springfox-swagger2</artifactId>
                <version>2.6.1</version>
            </dependency>
            <dependency>
                <groupId>io.springfox</groupId>
                <artifactId>springfox-swagger-ui</artifactId>
                <version>2.6.1</version>
            </dependency>
2.初始化数据库脚本
    init.sql

3.增加配置
    数据源配置：DataSourceConfig
    Redis配置：RedisConfig
4.使用
    4.1模拟并发售票：LockController
    4.2模拟编程式事务：TransController
        使用事务模板提交-spring编程式事务
        使用事务管理器-java编程式事务

5. 入口==》通过swagger访问：http://localhost:8090/swagger-ui.html