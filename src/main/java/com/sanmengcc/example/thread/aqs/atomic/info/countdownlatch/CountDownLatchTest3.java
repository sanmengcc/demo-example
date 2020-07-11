package com.sanmengcc.example.thread.aqs.atomic.info.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @ClassNameCountDownLatchTest2
 * @Description
 * @Author huhd
 * @Date2020/7/10 20:49
 * @Version V1.0
 **/
public class CountDownLatchTest3 {

    public static void main(String[] args) throws InterruptedException {

        //构造函数
        final CountDownLatch latch = new CountDownLatch(1);

        //await
        /*
         * 1.陷入等待
         * 2.new CountDownLatch(1) 中的count==0才会释放
         */
        latch.await();

        //await
        /*
         * 1.陷入等待、并且设置等待超时时间
         * 2.new CountDownLatch(1) 中的count==0才会释放
         */
        latch.await(10, TimeUnit.MILLISECONDS);

        //countDown
        /*
         * 1.释放count
         */
        latch.countDown();

        //count
        /*
         * 1.获取当前还存在的count
         */
        latch.getCount();



    }
}
