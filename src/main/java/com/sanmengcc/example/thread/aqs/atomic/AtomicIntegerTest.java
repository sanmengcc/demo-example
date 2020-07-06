package com.sanmengcc.example.thread.aqs.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntBinaryOperator;

/**
 * @ClassNameAtomicIntegerTest
 * @Description
 * @Author sanmengcc
 * @Date2020/7/3 11:26
 * @Version V1.0
 **/
public class AtomicIntegerTest {

    private volatile int value;

    public static void main(String[] args) {
        //create
        AtomicInteger i = new AtomicInteger();
        System.out.println(i.get());

        i = new AtomicInteger(10);
        System.out.println(i.get());

        //set
        /*
         * 1.set方法在安全方面并没有做什么处理，更多的是初始化操作
         * 2.如果A线程在get的时候，B线程set了数据那么A线程毫无疑问的拿到的是B线程set的数据
         */
        i.set(100);
        System.out.println(i.get());

        //lazySet
        /*
         * 1.只会在用的时候才去设置
         * 2.那么A线程使用lazySet，B线程可能在很长一段时间内也是可以获取的旧值的
         */
        i.lazySet(101);
        System.out.println(i.get());

        //getAndSet 原子操作获取旧值设置新值
        System.out.println(i.getAndSet(102));
        System.out.println(i.get());

        //compareAndSet
        /*
         * 1.判断预期值 == 实际值，相等则更新
         * 2.返回值代表是否修改成功
         */
        i.compareAndSet(103, 104);
        System.out.println(i.get());
        i.compareAndSet(102, 104);
        System.out.println(i.get());

        //weakCompareAndSet
        /*
         * 1.和compareAndSet作用差不多
         * 2.可能意外失败并且不提供排序保证
         */
        i.weakCompareAndSet(103, 104);
        System.out.println(i.get());
        i.weakCompareAndSet(102, 104);
        System.out.println(i.get());

        //accumulateAndGet
        /*
         * 1.该函数返回当前对象的更新值
         * 2.IntBinaryOperator 作为一个操作函数
         */
        IntBinaryOperator accumulatorFunction = (x, y) -> (x * y);
        System.out.println(i.accumulateAndGet(20, accumulatorFunction));

        //addAndGet
        /*
         * 1.指定添加的值
         */
        System.out.println(i.addAndGet(1));

        //decrementAndGet
        /*
         * 1.自减1操作
         */
        System.out.println(i.decrementAndGet());

        //doubleValue
        System.out.println(i.doubleValue());

        //floatValue
        System.out.println(i.floatValue());

        //get
        System.out.println(i.get());

        //getAndAccumulate
        /*
         * 1.先get然后执行函数操作
         */
        IntBinaryOperator op = (x, y) -> (x - y);
        System.out.println(i.getAndAccumulate(2000, op));
        System.out.println(i.get());

        //getAndAdd
        /*
         * 1.先get然后再Add指定的数字
         */
        System.out.println(i.getAndAdd(10));
        System.out.println(i.get());

        //getAndDecrement
        /*
         * 1.先get然后再自减1
         */
        System.out.println(i.getAndDecrement());
        System.out.println(i.get());

        //getAndIncrement
        /*
         * 1.先get然后再自增1
         */
        System.out.println(i.getAndIncrement());
        System.out.println(i.get());

        //getAndUpdate
        /*
         * 1.先get然后再执行一个Function
         */
        System.out.println(i.getAndUpdate((x) -> x - 20));
        System.out.println(i.get());

        //incrementAndGet
        /*
         * 1.先自增1再get
         */
        System.out.println(i.incrementAndGet());

        //intValue
        System.out.println(i.intValue());

        //longValue
        System.out.println(i.longValue());

        //updateAndGet
        /*
         * 1.先执行Function并且get
         */
        System.out.println(i.updateAndGet((x) -> x + 100));

    }
}
