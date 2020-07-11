package com.sanmengcc.example.thread.aqs.atomic.info.exchanger;

import java.util.concurrent.Exchanger;

/**
 * @ClassNameExchangerTest
 * @Description
 * @Author sanmeng
 * @Date2020/7/11 23:59
 * @Version V1.0
 **/
public class ExchangerTest {

    public static void main(String[] args) {
        test01();
    }

    public static void test01() {
        final Exchanger<String> exchanger = new Exchanger<>();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start.");
            try {
                String result = exchanger.exchange("I am come from A");
                System.out.println(Thread.currentThread().getName() + " Get value:" + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " Thread stop.");
        },"A").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start.");
            try {
                String result = exchanger.exchange("I am come from B");
                System.out.println(Thread.currentThread().getName() + " Get value:" + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " Thread stop.");
        },"B").start();
    }
}
