package com.sanmengcc.example.thread.aqs.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @ClassNameAtomicBooleanTest
 * @Description
 * @Author sanmengcc
 * @Date2020/7/5 19:48
 * @Version V1.0
 **/
public class AtomicBooleanTest {

    public static void main(String[] args) {
        //构造函数
        AtomicBoolean a1 = new AtomicBoolean();
        AtomicBoolean a2 = new AtomicBoolean(true);
        AtomicBoolean a3 = new AtomicBoolean(false);
        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a3);

        //compareAndSet
        /*
         * 1如果期望值相同就更新
         */
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        atomicBoolean.compareAndSet(false, true);
        System.out.println("compareAndSet=" + atomicBoolean.get());

        //get
        System.out.println(atomicBoolean.get());

        //getAndSet
        /*
         * 1。获取值以后set一个新的值
         */
        System.out.println(atomicBoolean.getAndSet(false));

        //lazySet
        atomicBoolean.lazySet(true);
        System.out.println(atomicBoolean.get());

        //set
        atomicBoolean.set(false);
        System.out.println(atomicBoolean.get());

        //weakCompareAndSet
        System.out.println(atomicBoolean.weakCompareAndSet(false, true));
    }
}
