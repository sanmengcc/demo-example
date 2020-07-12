package com.sanmengcc.example.thread.aqs.atomic.info.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassNameExchangerTest
 * @Description
 * @Author sanmeng
 * @Date2020/7/11 23:59
 * @Version V1.0
 **/
public class ExchangeTest {

    public static void main(String[] args) {
        test03();
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


    public static void test02() {
        final Exchanger<Object> exchanger = new Exchanger<>();

        new Thread(() -> {
            Object aobj = new Object();
            System.out.println("A will be send object:" + aobj);
            try {
                Object exchange = exchanger.exchange(aobj);
                System.out.println("A get result object:" + exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "A").start();

        new Thread(() -> {
            Object aobj = new Object();
            System.out.println("B will be send object:" + aobj);
            try {
                Object exchange = exchanger.exchange(aobj);
                System.out.println("B get result object:" + exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "B").start();
    }

    public static void test03() {
        final Exchanger<Integer> exchanger = new Exchanger<>();

        new Thread(() -> {
            AtomicInteger integer = new AtomicInteger(1);
            while (true) {
                try {
                    Integer exchange = exchanger.exchange(integer.get());
                    System.out.println("A send value:" + integer.get());
                    integer.set(exchange);
                    System.out.println("A get value:" + integer.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            AtomicInteger integer = new AtomicInteger(2);
            while (true) {
                try {
                    Integer exchange = exchanger.exchange(integer.get());
                    System.out.println("B send value:" + integer.get());
                    integer.set(exchange);
                    System.out.println("B get value:" + integer.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
    }
}
