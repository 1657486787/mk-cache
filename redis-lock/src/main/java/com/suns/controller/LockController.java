/**
 * Project Name:mk-cache <br>
 * Package Name:com.suns.controller <br>
 *
 * @author mk <br>
 * Date:2019-1-11 15:25 <br>
 */

package com.suns.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;

/**
 * ClassName: LockController <br>
 * Description:  <br>
 * @author mk
 * @Date 2019-1-11 15:25 <br>
 * @version
 */
@Api(value = "锁机制",description = "锁机制说明")
@RestController
public class LockController {

    private static long count = 20;//总票数
    private static int threadCount = 5;//5线程并发抢票
    private CountDownLatch cdl = new CountDownLatch(threadCount);

//    @Resource(name="redisLock")
    @Resource(name="mysqlLock")
    private Lock lock;

    @ApiOperation(value = "模拟售票")
    @RequestMapping(value = "/sale",method = RequestMethod.GET)
    public Long query(){

        for(int i=0;i<threadCount;i++){
            new Thread(new TicketRunnable()).start();
        }
        return count;
    }

    // 线程类模拟一个窗口买火车票
    public class TicketRunnable implements Runnable{

        private int amount = 0;
        @Override
        public void run() {

            System.out.println(Thread.currentThread().getName() + "开始售票");
            cdl.countDown();
            if (cdl.getCount()==0){
                System.out.println("----------售票结果------------------------------");
            }
            try {
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while(count > 0){
                lock.lock();
                try {
                    if (count > 0) {
                        //模拟卖票
                        amount++;
                        count--;
                    }
                }finally{
                    lock.unlock();
                }

                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "售出"+ (amount) + "张票");
        }
    }
}
