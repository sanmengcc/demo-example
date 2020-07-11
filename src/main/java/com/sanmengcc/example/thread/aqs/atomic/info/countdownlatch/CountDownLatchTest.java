package com.sanmengcc.example.thread.aqs.atomic.info.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassNameCountDownLatchTest
 * @Description
 * @Author sanmengcc
 * @Date2020/7/10 2:59
 * @Version V1.0
 **/
public class CountDownLatchTest {


    public static void main(String[] args) throws InterruptedException {
        test01();
    }

    private static Random random = new Random(System.currentTimeMillis());

    private static ExecutorService executor = Executors.newFixedThreadPool(2);

    private static final CountDownLatch latch = new CountDownLatch(10);

    public static void test01() throws InterruptedException {
        //1.获取数据
        final int[] data = query();
        //2.执行任务
        for (int i = 0; i < data.length; i++) {
            executor.execute(new SimpleRunnable(data, i, latch));
        }
        //3.等待任务结束
        latch.await();
        System.out.println("all of finish down.");
        //4.关闭线程池
        executor.shutdown();
    }

    static class SimpleRunnable implements Runnable {
        //数据data
        private final int[] data;

        //访问的数据索引位置
        private final int index;

        //countDown
        private final CountDownLatch countDownLatch;

        public SimpleRunnable(int[] data, int index, CountDownLatch countDownLatch) {
            this.data = data;
            this.index = index;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //任务执行单元
            int value = data[index];
            if (value % 2 == 0) {
                data[index] = value * 2;
            }
            else{
                data[index] = value * 10;
            }
            //latch关闭
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + " finish down.");
        }
    }

    //数据查询
    public static int[] query() {
        return new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    }
}
