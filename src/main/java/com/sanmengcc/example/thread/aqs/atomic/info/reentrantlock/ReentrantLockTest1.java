package com.sanmengcc.example.thread.aqs.atomic.info.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassNameReetrantLockTest
 * @Description
 * @Author sanmeng
 * @Date2020/7/12 17:55
 * @Version V1.0
 **/
public class ReentrantLockTest1 {

    final static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args)  {
        test02();
    }

    public static void test02() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    reentrantLock.lock();
                    System.out.println("A get lock.");
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                    System.out.println("A unlock.");
                }
            }
        },"A").start();

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reentrantLock.lockInterruptibly();
                    System.out.println("B get lock.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                    System.out.println("B unlock.");
                }
            }
        }, "B");
        threadB.start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadB.interrupt();
    }


    public static void test01() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    reentrantLock.lock();
                    System.out.println("A get lock.");
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                    System.out.println("A unlock.");
                }
            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    reentrantLock.lock();
                    System.out.println("B get lock.");
                }finally {
                    reentrantLock.unlock();
                    System.out.println("B unlock.");
                }
            }
        },"B").start();
    }

}
