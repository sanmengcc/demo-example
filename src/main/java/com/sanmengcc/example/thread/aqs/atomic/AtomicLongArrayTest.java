package com.sanmengcc.example.thread.aqs.atomic;

import java.util.concurrent.atomic.AtomicLongArray;

/**
 * @ClassNameAtomicLongArrayTest
 * @Description
 * @Author sanmengcc
 * @Date2020/7/6 17:14
 * @Version V1.0
 **/
public class AtomicLongArrayTest {
    public static void main(String[] args) {

        //构造函数
        AtomicLongArray a1 = new AtomicLongArray(1);
        System.out.println(a1.get(0));
        long[] a3 = {1,2,3};
        AtomicLongArray a2 = new AtomicLongArray(a3);
        System.out.println(a2.get(0));
        System.out.println(a2.get(1));

        //accumulateAndGet
        /*
         * 1.第一个参数为array对应的索引
         * 2.第二个参数为传入的操作值
         * 3.第三个参数为IntBinaryOperator操作函数
         * 4.使用索引i位置上的数据进行IntBinaryOperator函数操作
         * 5.再执行get
         */
        AtomicLongArray array = new AtomicLongArray(1);
        array.accumulateAndGet(0, 2, (x, y) -> x + y);
        System.out.println(array.get(0));

        //addAndGet
        /*
         * 1.第一个参数为array对应的索引
         * 2.索引对应的值进行add操作
         * 5.再执行get
         */
        System.out.println(array.addAndGet(0, 10));

        //compareAndSet
        /*
         * 1.第一个参数为array对应的索引
         * 2.索引对应的值进行对比
         * 5.如果对比成功再执行update操作
         */
        System.out.println(array.compareAndSet(0, 12, 20));
        System.out.println(array.get(0));

        //decrementAndGet
        /*
         * 1.针对索引处的值进行自减1操作
         * 2.再get
         */
        System.out.println(array.decrementAndGet(0));

        //get
        /*
         * 获取指定索引上的值
         */
        System.out.println(array.get(0));

        //getAndAccumulate
        /*
         * 1.先获取指定索引上的值
         * 2.执行IntBinaryOperator函数操作
         */
        System.out.println(array.getAndAccumulate(0, 10, (x, y) -> x - y));

        //getAndAdd
        /*
         * 1.先get然后add指定数值
         */
        System.out.println(array.getAndAdd(0, 10));

        //getAndDecrement
        /*
         * 1.先get指定索引处的数值然后进行自减1
         */
        System.out.println(array.getAndDecrement(0));

        //getAndIncrement
        /*
         * 1.先get指定索引处的数值然后进行自增1
         */
        System.out.println(array.getAndIncrement(0));

        //getAndSet
        /*
         * 1.先get执行索引处的值人，然后set一个指定的数值
         */
        System.out.println(array.getAndSet(0, 20));

        //getAndUpdate
        /*
         * 1.先get指定索引处的值
         * 2.再执行IntUnaryOperator函数
         */
        System.out.println(array.getAndUpdate(0, x -> x - 20));

        //incrementAndGet
        /*
         * 1.先对指定索引处的值自增1
         * 2.再get
         */
        System.out.println(array.incrementAndGet(0));

        //lazySet
        array.lazySet(0, 1000);
        System.out.println(array.get(0));

        //length
        System.out.println(array.length());

        //set
        array.set(0, 200);
        System.out.println(array.get(0));

        //updateAndGet
        /*
         * 1.先将指定索引处的值执行IntUnaryOperator函数
         * 2.再get
         */
        System.out.println(array.updateAndGet(0, x -> x + 100));

        //weakCompareAndSet
        System.out.println(array.weakCompareAndSet(0, 10, 20));

    }
}
