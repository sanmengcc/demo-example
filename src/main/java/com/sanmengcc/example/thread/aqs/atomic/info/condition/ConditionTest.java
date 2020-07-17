package com.sanmengcc.example.thread.aqs.atomic.info.condition;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassNameConditionTest
 * @Description
 * @Author sanmengcc
 * @Date2020/7/17 20:29
 * @Version V1.0
 **/
public class ConditionTest {

    public static void main(String[] args) throws InterruptedException {

        final ReentrantLock lock = new ReentrantLock();

        //初始化
        Condition condition = lock.newCondition();

        //await
        /*
         * 1.当前线程进入等待状态
         * 2.线程中断或者signal
         * 3.支持设置超时时间
         */
        condition.await();
        condition.await(10, TimeUnit.MILLISECONDS);

        //awaitNanos
        /*
         * 1.当前线程进入等待状态
         * 2.线程中断或者signal
         * 3.超时
         * 4.返回值表示剩余超时时间
         */
        condition.awaitNanos(10);

        //awaitUninterruptibly
        /*
         * 1.当前线程进入等待状态
         * 2.只能等待通知
         * 3.不响应中断
         */
        condition.awaitUninterruptibly();

        //awaitUntil
        /*
         * 1.当前线程进入等待状态
         * 2.通知、线程中断、执行操作退出
         * 3.若是在时间范围内被通知返回true,否则false
         */
        condition.awaitUntil(new Date());

        //signal
        /*
         * 1.唤醒一个在Condition等待的线程
         * 2.必须获得Condition相关联的锁
         */
        condition.signal();

        //signalAll
        /*
         * 1.唤醒所有在Condition等待的线程
         * 2.必须获得Condition相关联的锁
         */
        condition.signalAll();

    }
}
