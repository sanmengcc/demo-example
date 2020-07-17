package com.sanmengcc.example.thread.aqs.atomic.info.readwritelock;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassNameReentrantReadWriteLockTest
 * @Description
 * @Author sanmengcc
 * @Date2020/7/13 20:29
 * @Version V1.0
 **/
public class ReentrantReadWriteLockTest {

    public static void main(String[] args) {

        //构造函数
        ReentrantReadWriteLock r1 = new ReentrantReadWriteLock();
        //可以通过构造函数指定公平策略
        ReentrantReadWriteLock r2 = new ReentrantReadWriteLock(true);

        ReentrantReadWriteLock reentrantLock = new ReentrantReadWriteLock();

        //getQueueLength
        /*
         * 1.返回等待获取读取或写入锁定的线程数的估计
         */
        System.out.println(reentrantLock.getQueueLength());

        //getReadHoldCount
        /*
         * 1.查询当前线程对此锁的可重入读取保留数
         */
        System.out.println(reentrantLock.getReadHoldCount());

        //getReadLockCount
        /*
         * 1.查询为此锁持有的读取锁的数量
         */
        System.out.println(reentrantLock.getReadLockCount());

        //getWaitQueueLength
        /*
         * 1.返回与写入锁相关联的给定条件等待的线程数的估计
         */
        System.out.println(reentrantLock.getWaitQueueLength(null));

        //getWriteHoldCount
        /*
         * 1.查询当前线程对此锁的可重入写入数量
         */
        System.out.println(reentrantLock.getWriteHoldCount());

        //hasQueuedThread
        /*
         * 1.查询给定线程是否等待获取读取或写入锁定
         */
        System.out.println(reentrantLock.hasQueuedThread(Thread.currentThread()));

        //hasQueuedThreads
        /*
         * 1.查询是否有任何线程正在等待获取读取或写入锁定
         */
        System.out.println(reentrantLock.hasQueuedThreads());

        //hasWaiters
        /*
         * 1.查询任何线程是否等待与写锁相关联的给定条件
         */
        System.out.println(reentrantLock.hasWaiters(null));

        //isFair
        /*
         * 1.如果此锁的公平设置为true，则返回 true
         */
        System.out.println(reentrantLock.isFair());

        //isWriteLocked
        /*
         * 1.查询写锁是否由任何线程持有
         */
        System.out.println(reentrantLock.isWriteLocked());

        //isWriteLockedByCurrentThread
        /*
         * 1.查询写锁是否由当前线程持有
         */
        System.out.println(reentrantLock.isWriteLockedByCurrentThread());

        //readLock
        /*
         * 1.返回用于阅读的锁
         */
        System.out.println(reentrantLock.readLock());

        //writeLock
        /*
         * 1.返回用于写入的锁
         */
        System.out.println(reentrantLock.writeLock());


    }
}
