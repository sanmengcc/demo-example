package com.sanmengcc.example.thread.aqs.atomic.info.condition;

import java.io.InputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @ClassNameConditionTest
 * @Description
 * @Author sanmengcc
 * @Date2020/7/17 20:29
 * @Version V1.0
 **/
public class ConditionTest1 {

    /**
     * 锁
     */
    final static ReentrantLock lock = new ReentrantLock();

    /**
     * 生产者
     */
    final static Condition PRODUCE = lock.newCondition();

    /**
     * 消费者
     */
    final static Condition CONSUMER = lock.newCondition();

    /**
     * 任务队列
     */
    final static LinkedList<Integer> QUEUE = new LinkedList<>();

    final static Integer MAX_QUEUE_SIZE = 4;

    final static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {
        IntStream.range(0, 6).boxed().forEach(ConditionTest1::startProduce);
        IntStream.range(0, 10).boxed().forEach(ConditionTest1::startConsumer);
    }

    private static void startProduce(int i) {
        new Thread(() -> {
           for(;;){
               produce();
               try {
                   TimeUnit.SECONDS.sleep(1);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        }, "P-" + i).start();
    }

    private static void startConsumer(int i) {
        new Thread(() -> {
            for(;;){
                consumer();
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C-" + i).start();
    }

    /**
     * @Description 生产
     * @Author  sanmengccc
     * @Date   2020/7/17 20:48
     */
    private static void produce() {
        try {
            lock.lock();
            while (QUEUE.size() > MAX_QUEUE_SIZE) {
                PRODUCE.await();
            }
            int value = random.nextInt(1000);
            System.out.println("The " + Thread.currentThread().getName() + " produce:" + value);
            QUEUE.addLast(value);
            CONSUMER.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    /**
     * @Description 消费
     * @Author  sanmengcc
     * @Date   2020/7/17 20:49
     */
    private static void consumer() {
        try {
            lock.lock();
            while (QUEUE.isEmpty()) {
                CONSUMER.await();
            }
            System.out.println("The " + Thread.currentThread().getName() + " consumer:" + QUEUE.removeFirst());
            TimeUnit.SECONDS.sleep(1);
            PRODUCE.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
