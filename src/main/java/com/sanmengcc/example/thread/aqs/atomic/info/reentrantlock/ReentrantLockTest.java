package com.sanmengcc.example.thread.aqs.atomic.info.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassNameReetrantLockTest
 * @Description
 * @Author sanmeng
 * @Date2020/7/12 17:55
 * @Version V1.0
 **/
public class ReentrantLockTest {

    public static void main(String[] args)  {
        //构造函数
        ReentrantLock reentrant1 = new ReentrantLock();
        //可以设置公平策略
        ReentrantLock reentrant2 = new ReentrantLock(true);

        ReentrantLock reentrantLock = new ReentrantLock();

        //getHoldCount
        /*
         * 1.查询当前线程保持此锁定的个数(调用lock()方法的次数)
         */
        reentrantLock.getHoldCount();

        //getQueueLength
        /*
         * 1.获取正等待获取此锁定的线程数
         */
        System.out.println(reentrantLock.getQueueLength());

        //hasQueuedThread
        /*
         * 1.判断线程是否处于等待队列之中
         */
        System.out.println(reentrantLock.hasQueuedThread(Thread.currentThread()));

        //hasQueuedThreads
        /*
         * 1.判断是否有线程处于等待队列之中
         */
        System.out.println(reentrantLock.hasQueuedThreads());

        //hasWaiters
        /*
         * 1.查询是否有线程正在等待与此锁定有关的Condition条件
         */
        System.out.println(reentrantLock.hasWaiters(null));

        //isFair
        /*
         * 1.判断锁策略公平锁还是非公平锁
         */
        System.out.println(reentrantLock.isFair());

        //isHeldByCurrentThread
        /*
         * 1.查询当前线程是否保持此锁定
         */
        System.out.println(reentrantLock.isHeldByCurrentThread());

        //isLocked
        /*
         * 1.查询此锁定是否由任意线程保持
         */
        System.out.println(reentrantLock.isLocked());

        //lock & unlock
        /*
         * 1.获取锁
         * 2.释放锁
         */
        try {
            reentrantLock.lock();
        } catch (Exception e) {

        }finally {
            reentrantLock.unlock();
        }

        //lockInterruptibly
        /*
         * 1.线程在请求lock并被阻塞时，如果被interrupt，则此线程会被唤醒并被要求处理InterruptedException
         */
        try {
            reentrantLock.lockInterruptibly();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //newCondition
        /*
         * 1.创建一个新的Condition实例
         */
        System.out.println(reentrantLock.newCondition());

        //tryLock
        /*
         * 1.尝试获取锁，如果获取成功，则返回true，如果获取失败（即锁已被其他线程获取），则返回false
         * 2。支持设置超时时间
         */
        System.out.println(reentrantLock.tryLock());
        try {
            System.out.println(reentrantLock.tryLock(10, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
