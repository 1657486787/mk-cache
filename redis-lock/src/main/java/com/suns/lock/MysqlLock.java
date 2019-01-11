/**
 * Project Name:mk-cache <br>
 * Package Name:com.suns.lock <br>
 *
 * @author mk <br>
 * Date:2019-1-11 15:34 <br>
 */

package com.suns.lock;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * ClassName: MysqlLock <br>
 * Description:  <br>
 * @author mk
 * @Date 2019-1-11 15:34 <br>
 * @version
 */
@Component
public class MysqlLock implements Lock {

    private static final int ID_NUM = 1;

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * Description: 阻塞式加锁 <br>
     * @author mk
     * @Date 2019-1-11 15:39 <br>
     * @Param
     * @return
     */
    @Override
    public void lock() {
        //1.尝试加锁
        if(tryLock()){
            return;
        }

        //2.加锁失败,当前任务休眠
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        }catch (Exception e){
            e.printStackTrace();
        }

        //3.递归调用，再次重新加锁
        lock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    /**
     * Description: //非阻塞式加锁,往数据库写入id为1的数据，能写成功的即加锁成功 <br>
     * @author mk
     * @Date 2019-1-11 15:40 <br>
     * @Param
     * @return
     */
    @Override
    public boolean tryLock() {
        try {
            String sql = "insert into db_lock (id) values (?)";
            jdbcTemplate.update(sql,ID_NUM);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        String sql = "delete from db_lock where id = ?";
        jdbcTemplate.update(sql,ID_NUM);
//		mapper.deleteByPrimaryKey(ID_NUM);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
