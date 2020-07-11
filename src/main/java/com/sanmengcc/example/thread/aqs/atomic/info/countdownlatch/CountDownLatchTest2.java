package com.sanmengcc.example.thread.aqs.atomic.info.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassNameCountDownLatchTest2
 * @Description
 * @Author huhd
 * @Date2020/7/10 20:49
 * @Version V1.0
 **/
public class CountDownLatchTest2 {

    public static void main(String[] args) throws InterruptedException {

        final CountDownLatch countDownLatch = new CountDownLatch(1);

        //开启一个线程执行某些前置操作
        new Thread() {
            @Override
            public void run() {
                //前置操作
                System.out.println("Do some initial working.");
                //执行完毕发现提交不满足需要其他条件那么此时进入等待
                try {
                    Thread.sleep(1000);
                    countDownLatch.await();
                    //等待结束继续执行
                    System.out.println("Do other working...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();


        //开启一个线程执行某些前置操作
        new Thread() {
            @Override
            public void run() {
                //前置操作
                System.out.println("Do some initial working.");
                //执行完毕发现提交不满足需要其他条件那么此时进入等待
                try {
                    Thread.sleep(1000);
                    countDownLatch.await();
                    //等待结束继续执行
                    System.out.println("Do other working...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        //另一个线程也可以并行的执行前置操作但是上面一个线程需要当前线程的一些条件
        new Thread(){
            @Override
            public void run() {
                //执行前置条件
                System.out.println("asyn prepare for some data.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //最终条件满足，使得另一个等待的线程再次唤醒执行任务
                    countDownLatch.countDown();
                }
            }
        }.start();

        Thread.currentThread().join();
    }
}
