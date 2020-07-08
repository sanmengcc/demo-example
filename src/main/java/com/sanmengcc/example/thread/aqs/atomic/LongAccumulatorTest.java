package com.sanmengcc.example.thread.aqs.atomic;

import java.util.concurrent.atomic.LongAccumulator;

/**
 * @ClassNameLongAccumulatorTest
 * @Description
 * @Author sanmengcc
 * @Date2020/7/7 22:03
 * @Version V1.0
 **/
public class LongAccumulatorTest {

    public static void main(String[] args) {

        //构造函数
        LongAccumulator longAccumulator = new LongAccumulator((x, y) -> x * y, 10);

        //accumulate
        /*
         * 1.传入数值
         * 2.执行LongBinaryOperator操作函数
         */
        longAccumulator.accumulate(10);
        System.out.println(longAccumulator.doubleValue());

        //doubleValue
        System.out.println(longAccumulator.doubleValue());

        //floatValue
        System.out.println(longAccumulator.floatValue());

        //get
        System.out.println(longAccumulator.get());

        //getThenReset
        /*
         * 1.先get
         * 2.还原数据
         */
        System.out.println(longAccumulator.getThenReset());

        //intValue
        System.out.println(longAccumulator.intValue());

        //longValue
        System.out.println(longAccumulator.longValue());

        //reset
        longAccumulator.reset();
        System.out.println(longAccumulator.get());
    }
}
