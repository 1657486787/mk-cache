缓存实战
1.spring cache的使用
    演示从使用redisTemplate 到 spring cache，为什么要使用spring cache? ==> 缓存的流程是固定的，但是具体的实现缓存方案是多种多样的，如redis,ConcurrentMapCache,memcached等
    spring cache 是spring3.1后自带的
    1.1springmvc springmvc-cache
    1.2springboot-cache
        redis 实现缓存
        spring cache实现缓存
        缓存雪崩的解决方案
        缓存穿透的解决方案
2.分布式锁机制
    实现方式有mysql/redis/zookeeper
    mysql:简单，性能低
    redis:性能高，可能出现死锁
    zookeeper:性能高

    详见reids-lock

3.分布式事务
    1.2PC（Two-phase Commit）--很少用
        1.1 X/open（XA）组织提出分布式事务规范，2PC是对XA的实现
        1.2 2pc在现实使用中的问题/限制
            1.必须要使用支持XA协议的datasource数据源
            2.因为要同时锁定两个数据库的数据，事务锁定时间大大延长 ------ 现实中数据库性能欠佳

        1.3 详见 trans-2pc

    2.TCC（try-confirm-cancel）
        2.1 TCC其实就是采用补偿机制，其核心思想是：针对每个操作，都有一个对应的确认和补偿（撤销）操作。

        2.2 业务接口改造：
        一个普通业务接口-----切成三个接口：try--confirm--cancel
            try    ------- 原业务接口------- 接口执行完毕，数据库数据就提交 ----如： insert一条数据1
            confirm ------ 确认数据 --------- 将try阶段的数据查询出来确认，如： select 1确认数据是否已ok
            cancel -------- 抵消数据 --------- 将try阶段的数据做逆向取消，如： delete from删除数据/ insert  -1再加一条数据抵消  ------- 逆向业务方法，又称之谓补偿动作
        2.3 try -confirm-cancel的三个步骤联动过程
            如果try不出异常，正常结束，则框架自动去调用confirm方法
            如果try出了异常，框架去调用cancel补偿方法
        2.4 tcc方案，对原业务的侵入性太强。为了更好控制，通常做变种使用
            try    ------- 将原业务接口，涉及到的资源锁定/或者做预备 ------ 通过一个冻结状态，做排它处理
            confirm ------ 正式调用原业务接口方法，如：insert一条数据1 ----- 因try阶段已经为此语句准备好了执行条件，不会再出现业务的冲突情况（业务一定是可行的）
            cancel -------- 恢复资源状态，取消资源的冻结状态

        2.5 详见 tcc-one,tcc-two 两个项目

    3.MQ 事务消息--很少用
        有一些第三方的MQ是支持事务消息的，比如RocketMQ，他们支持事务消息的方式也是类似于采用的二阶段提交，但是市面上一些主流的MQ都是不支持事务消息的，比如 RabbitMQ 和 Kafka 都不支持
        以阿里的 RocketMQ 中间件为例，其思路大致为：
        第一阶段Prepared消息，会拿到消息的地址。
        第二阶段执行本地事务
        第三阶段通过第一阶段拿到的地址去访问消息，并修改状态。

