package com.sanmengcc.example.thread.aqs.atomic.info.semphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @ClassNameSemphoreTest
 * @Description
 * @Author sanmengcc
 * @Date2020/7/12 8:45
 * @Version V1.0
 **/
public class SemaphoreTest {

    public static void main(String[] args) throws InterruptedException {

        //构造函数
        Semaphore semaphore = new Semaphore(1);
        //Semaphore semaphore = new Semaphore(1,true);

        //acquire
        /*
         * 1.获取许可证
         * 2.获取指定数量的许可证
         */
        semaphore.acquire();
        semaphore.acquire(10);

        //release
        /*
         * 1.释放许可证
         * 2.释放前必须先获得
         * 3.释放指定数量的许可证
         */
        semaphore.release();
        semaphore.release(10);

        //tryAcquire
        /*
         * 1.尝试获取一个许可证
         * 2.成功返回：true,失败返回：false
         * 3.尝试获取多个许可证
         */
        boolean tryAcquire = semaphore.tryAcquire();
        semaphore.tryAcquire(10);
        //尝试获取多个并且具有超时时间
        semaphore.tryAcquire(10, 10, TimeUnit.MILLISECONDS);
        //尝试获取一个并且具有超时时间
        semaphore.tryAcquire(10, TimeUnit.MILLISECONDS);

        //acquireUninterruptibly
        /*
         * 1.从此信号量中获取许可，在有可用的许可前将其阻塞
         * 2.从此信号量获取给定数目的许可，在提供这些许可前一直将线程阻塞
         */
        semaphore.acquireUninterruptibly();
        semaphore.acquireUninterruptibly(10);

        //availablePermits
        /*
         * 1.返回此信号量中当前可用的许可数
         */
        System.out.println(semaphore.availablePermits());

        //drainPermits
        /*
         * 1.获取并返回立即可用的所有许可。
         */
        System.out.println(semaphore.drainPermits());

        //getQueueLength
        /*
         * 1.返回正在等待获取的线程的估计数目
         */
        System.out.println(semaphore.getQueueLength());

        //hasQueuedThreads
        /*
         * 1.查询是否有线程正在等待获取
         */
        System.out.println(semaphore.hasQueuedThreads());

        //isFair
        /*
         * 1.如果此信号量的公平设置为 true，则返回 true
         */
        System.out.println(semaphore.isFair());


    }

    public static void test01() {
        SemaphoreLock lock = new SemaphoreLock();
        IntStream.range(0,2).forEach(i->{
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " is Running.");
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " get Lock.");
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
                System.out.println(Thread.currentThread().getName() + " unlock.");
            }).start();
        });
    }



    //定义一个显示锁
    static class SemaphoreLock{

        private Semaphore semaphore = new Semaphore(1);
//        private Semaphore semaphore = new Semaphore(1,true);

        public void lock() throws InterruptedException {
            semaphore.acquire();
        }

        public void unlock() {
            semaphore.release();
        }
    }
}
