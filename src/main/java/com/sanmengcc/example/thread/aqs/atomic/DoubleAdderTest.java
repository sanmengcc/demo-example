package com.sanmengcc.example.thread.aqs.atomic;

import java.util.concurrent.atomic.DoubleAdder;

/**
 * @ClassNameDoubleAdderTest
 * @Description
 * @Author sanmengcc
 * @Date2020/7/7 21:56
 * @Version V1.0
 **/
public class DoubleAdderTest {

    public static void main(String[] args) {
        //构造函数
        DoubleAdder doubleAdder = new DoubleAdder();

        //add
        doubleAdder.add(10);
        System.out.println(doubleAdder.doubleValue());

        //doubleValue
        System.out.println(doubleAdder.doubleValue());

        //floatValue
        System.out.println(doubleAdder.floatValue());

        //intValue
        System.out.println(doubleAdder.intValue());

        //longValue
        System.out.println(doubleAdder.longValue());

        //reset
        doubleAdder.reset();
        System.out.println(doubleAdder.intValue());

        //sum
        System.out.println(doubleAdder.sum());

        //sumThenReset
        /*
         * 1.先sum
         * 2.再还原
         */
        System.out.println(doubleAdder.sumThenReset());

    }
}
