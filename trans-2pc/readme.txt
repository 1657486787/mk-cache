分布式事务-2PC（Two-phase Commit）
1.增加依赖

    <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-jdbc -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
        <version>${spring.boot.version}</version>
    </dependency>

    <!-- 2pc -->
    <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-jta-atomikos -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jta-atomikos</artifactId>
        <version>${spring.boot.version}</version>
    </dependency>

2.初始化数据库脚本
    init.sql
    建两个数据库，在不同数据库建表，使用2pc来管理分布式事务
3.增加配置
    数据源配置：DataSourceConfig
4.使用
    模拟下单操作：OrderController

    初始化数据时，库trans1只插入了4条数据，而trans2插入了5条数据。前四次执行会成功，执行第五次时，由于库trans1更新失败，所以事务将回滚，观察库trans2的数据是否有没更新。 没有被更新说明分布式事务成功了。

5. 入口==》通过swagger访问：http://localhost:8090/swagger-ui.html
