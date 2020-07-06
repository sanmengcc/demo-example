package com.sanmengcc.example.thread.aqs.atomic;

import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * @ClassNameAtomicReferenceArrayTest
 * @Description
 * @Author sanmengcc
 * @Date2020/7/6 17:24
 * @Version V1.0
 **/
public class AtomicReferenceArrayTest {

    public static void main(String[] args) {

        //构造函数
        //初始化AtomicReferenceArray数组长度
        AtomicReferenceArray<String> a1 = new AtomicReferenceArray(1);
        String[] arra2 = {"a1", "a2"};
        AtomicReferenceArray<String> a2 = new AtomicReferenceArray(arra2);

        //accumulateAndGet
        String[] array1 = {"a1", "a2"};

        //accumulateAndGet
        /*
         * 1.先指定索引处的数据执行BinaryOperator函数操作
         * 2.在获取操作后的数据
         */
        AtomicReferenceArray<String> array = new AtomicReferenceArray(array1);
        array.accumulateAndGet(0, "x", (x, y) -> x + y);

        //compareAndSet
        /*
         * 1.先使用索引处对应的数据对比预期值
         * 2.匹配成功则执行更改数据操作
         */
        System.out.println(array.compareAndSet(0, "a1x", "a1y"));

        //get
        /*
         * 1.获取指定索引处的数据
         */
        System.out.println(array.get(0));

        //getAndAccumulate
        /*
         * 1.先get指定索引处的数据
         * 2.再执行BinaryOperator函数操作
         */
        System.out.println(array.getAndAccumulate(0, "x", (x, y) -> x + y));

        //getAndSet
        /*
         * 1.先get指定索引处的数据
         * 2.再set指定数据
         */
        System.out.println(array.getAndSet(0, "a3"));

        //getAndUpdate
        /*
         * 1.先get指定索引处的数据
         * 2.再执行UnaryOperator函数操作数据
         */
        System.out.println(array.getAndUpdate(0, x -> x.toLowerCase()));

        //lazySet
        array.lazySet(0, "sanmengcc");

        //length
        System.out.println(array.length());

        //set
        array.set(0,"sanmengcc-1");

        //updateAndGet
        /*
         * 1.使用索引处的数据执行UnaryOperator函数
         * 2.再获取操作后的数据
         */
        System.out.println(array.updateAndGet(0, x -> x + "_sanmengcc"));

        //weakCompareAndSet
        System.out.println(array.weakCompareAndSet(0,"sanmengcc","sanmegcc-2"));

    }
}
