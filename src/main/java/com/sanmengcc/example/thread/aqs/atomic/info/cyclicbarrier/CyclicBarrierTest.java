package com.sanmengcc.example.thread.aqs.atomic.info.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @ClassNameCyclicBarrierTest
 * @Description
 * @Author sanmengcc
 * @Date2020/7/11 20:53
 * @Version V1.0
 **/
public class CyclicBarrierTest {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException, TimeoutException {
        test02();

    }

    public static void test() throws BrokenBarrierException, InterruptedException, TimeoutException {
        //构造函数
        /*
         * 1.任务数
         */
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        /*
         * 1.任务数
         * 2.任务所有执行完毕的一个回调函数
         */
        CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2,()->{
            System.out.println("我是一个执行完毕的回调函数！");
        });

        //await
        /*
         * 1.等待所有任务执行结束
         * 2.线程到达之后，会在条件上等待，直到最后一个线程到达，最后一个线程到达时会执行command
         * 3.可以使用携带超时时间的await
         */
        cyclicBarrier.await();
        cyclicBarrier.await(1, TimeUnit.MILLISECONDS);

        //getNumberWaiting
        /*
         * 1.获取已到达等待线程数量
         */
        System.out.println(cyclicBarrier.getNumberWaiting());

        //getParties
        /*
         * 1.获取需要等待的线程数
         */
        System.out.println(cyclicBarrier.getParties());

        //isBroken
        /*
         * 1.栅栏是否已被破解
         */
        System.out.println(cyclicBarrier.isBroken());

        //reset
        /*
         * 1.重置栅栏数据和状态
         */
        cyclicBarrier.reset();
    }

    public static void test01() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        new Thread(() -> {
            try {
                Thread.sleep(10000);
                System.out.println("T1 work finish.");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        },"T1").start();


        new Thread(() -> {
            try {
                Thread.sleep(15000);
                System.out.println("T2 work finish.");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        },"T2").start();

    }

    public static void test02() throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        new Thread(() -> {
            try {
                Thread.sleep(10000);
                System.out.println("T1 work finish.");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        },"T1").start();


        new Thread(() -> {
            try {
                Thread.sleep(15000);
                System.out.println("T2 work finish.");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        },"T2").start();

        cyclicBarrier.await();
        System.out.println("All work finish.");

    }
}
