package com.sanmengcc.example.thread.aqs.atomic;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.LongBinaryOperator;

/**
 * @ClassNameAtomicLongTest
 * @Description
 * @Author sanmengcc
 * @Date2020/7/5 21:20
 * @Version V1.0
 **/
public class AtomicLongTest {

    public static void main(String[] args) {
        //构造函数
        AtomicLong a1 = new AtomicLong();
        AtomicLong a2 = new AtomicLong(1L);
        System.out.println(a1);
        System.out.println(a2);

        AtomicLong atomicLong = new AtomicLong(10L);

        //accumulateAndGet
        /*
         * 1.先操作LongBinaryOperator函数
         * 2.后返回操作后的数据
         */
        LongBinaryOperator longBinaryOperator = (x, y) -> x + y;
        atomicLong.accumulateAndGet(10L, longBinaryOperator);
        System.out.println(atomicLong.get());

        //addAndGet
        /*
         * 1.先进行add操作
         * 2.后返回操作后的数据
         */
        System.out.println(atomicLong.addAndGet(10L));

        //compareAndSet
        /*
         * 1.判断预期值 == 实际值，相等则更新
         * 2.返回值代表是否修改成功
         */
        System.out.println(atomicLong.compareAndSet(30L, 40L));
        System.out.println(atomicLong.get());

        //decrementAndGet
        /*
         * 1.先进行自减1操作
         * 2.返回自减后的数据
         */
        System.out.println(atomicLong.decrementAndGet());

        //doubleValue
        System.out.println(atomicLong.doubleValue());

        //floatValue
        System.out.println(atomicLong.floatValue());

        //get
        System.out.println(atomicLong.get());

        //getAndAccumulate
        /*
         * 1.返回操作后的数据
         * 2.操作LongBinaryOperator函数
         */
        System.out.println(atomicLong.getAndAccumulate(20L, (x, y) -> x - y));
        System.out.println(atomicLong.get());

        //getAndAdd
        /*
         * 1.先get
         * 2.然后再执行add操作
         */
        System.out.println(atomicLong.getAndAdd(10L));
        System.out.println(atomicLong.get());

        //getAndDecrement
        /*
         * 1.先get
         * 2.然后再执行自减1
         */
        System.out.println(atomicLong.getAndDecrement());
        System.out.println(atomicLong.get());

        //getAndIncrement
        /*
         * 1.先get
         * 2.然后再执行自增1
         */
        System.out.println(atomicLong.getAndIncrement());
        System.out.println(atomicLong.get());

        //getAndSet
        /*
         * 1.先get
         * 2.然后再执行set操作
         */
        System.out.println(atomicLong.getAndSet(200L));
        System.out.println(atomicLong.get());

        //getAndUpdate
        /*
         * 1.先get
         * 2.然后再执行LongUnaryOperator操作函数
         */
        System.out.println(atomicLong.getAndUpdate((x) -> x - 100));
        System.out.println(atomicLong.get());

        //incrementAndGet
        /*
         * 1.先自增1
         * 2.再获取
         */
        System.out.println(atomicLong.incrementAndGet());

        //intValue
        System.out.println(atomicLong.intValue());

        //lazySet
        atomicLong.lazySet(2000L);

        //longValue
        System.out.println(atomicLong.longValue());

        //set
        atomicLong.set(300L);

        //updateAndGet
        /*
         * 1.先执行LongUnaryOperator操作函数
         * 2.然后再get
         */
        System.out.println(atomicLong.updateAndGet((x) -> x - 200));

        //weakCompareAndSet
        System.out.println(atomicLong.weakCompareAndSet(100L, 200L));
    }
}
