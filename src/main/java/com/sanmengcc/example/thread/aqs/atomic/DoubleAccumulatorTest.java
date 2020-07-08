package com.sanmengcc.example.thread.aqs.atomic;

import java.util.concurrent.atomic.DoubleAccumulator;

/**
 * @ClassNameDoubleAccumulatorTest
 * @Description
 * @Author sanmengcc
 * @Date2020/7/7 21:34
 * @Version V1.0
 **/
public class DoubleAccumulatorTest {

    public static void main(String[] args) {
        //构造函数
        DoubleAccumulator doubleAccumulator = new DoubleAccumulator((x, y) -> x + y, 10);
        System.out.println(doubleAccumulator.doubleValue());

        //accumulate
        /*
         * 1.传入值
         * 2.执行构造函数中的accumulatorFunction函数
         */
        doubleAccumulator.accumulate(10);
        System.out.println(doubleAccumulator.doubleValue());

        //doubleValue
        System.out.println(doubleAccumulator.doubleValue());

        //floatValue
        System.out.println(doubleAccumulator.floatValue());

        //get
        System.out.println(doubleAccumulator.get());

        //getThenReset
        /*
         * 1.先get
         * 2.再还原程初始值
         */
        System.out.println(doubleAccumulator.getThenReset());
        System.out.println(doubleAccumulator.get());

        //intValue
        System.out.println(doubleAccumulator.intValue());

        //longValue
        System.out.println(doubleAccumulator.longValue());

        //reset
        doubleAccumulator.reset();
        System.out.println(doubleAccumulator.get());

    }
}
