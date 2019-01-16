分布式事务-tcc（Try-Commit-Cancel）
1.增加依赖

    <!-- tcc start -->
    <dependency>
        <groupId>org.mengyun</groupId>
        <artifactId>tcc-transaction-dubbo</artifactId>
        <version>${tcc.version}</version>
    </dependency>

    <dependency>
        <groupId>org.mengyun</groupId>
        <artifactId>tcc-transaction-spring</artifactId>
        <version>${tcc.version}</version>
    </dependency>
    <!-- tcc end -->

    注意：需要自己下tcc-transaction的源码(https://github.com/changmingxie/tcc-transaction)，并执行mvn clean package -Dmaven.test.skip=true install ，将tcc的jar安装到本地
        1.如果执行mvn clean package -Dmaven.test.skip=true install 有错，可以将pom.xml中如下模板注释
        <modules>
            <module>tcc-transaction-core</module>
            <module>tcc-transaction-api</module>
            <module>tcc-transaction-spring</module>
            <module>tcc-transaction-dubbo</module>
            <!--
            <module>tcc-transaction-unit-test</module>
            <module>tcc-transaction-tutorial-sample</module>
            <module>tcc-transaction-server</module>
            -->
        </modules>
        2.tcc中默认spring的版本为3.2.12.RELEASE,将其修改为<springframework.version>4.3.4.RELEASE</springframework.version>

2.初始化数据库脚本
    init.sql
    建两个数据库，在不同数据库建表，使用tcc来管理分布式事务
    注意需要自己新建tcc的记录表：TCC_TRANSACTION_ORDER（表名tcc_transaction_order 其中tcc_transaction为固定的前缀，ORDER为自定义的）

3.增加配置

    tcc配置：tcc.xml

4.使用

    4.1依赖dubbo及zookeeper,需要先启动本地的zookeeper
    4.2先启动提供者tcc-two,端口8081
    4.3启动消费者tcc-one,端口8080
    4.4入口==》通过swagger访问：http://localhost:8080/swagger-ui.html

    模拟下单操作：GHController
    初始化数据时，库trans1只插入了4条数据，而trans2插入了5条数据。
    tcc-one通过rpc远程调用tcc-two,前四次执行会成功，执行第五次时，由于库trans1更新失败，所以事务将回滚，观察库trans2的数据是否有没更新。 没有被更新说明分布式事务成功了。

