package com.sanmengcc.example.thread.aqs.atomic;

import java.util.concurrent.atomic.LongAdder;

/**
 * @ClassNameLongAdderTest
 * @Description
 * @Author sanemngcc
 * @Date2020/7/7 22:04
 * @Version V1.0
 **/
public class LongAdderTest {

    public static void main(String[] args) {

        //构造函数
        LongAdder longAdder = new LongAdder();

        //add
        longAdder.add(10);
        System.out.println(longAdder.intValue());

        //decrement
        longAdder.decrement();
        System.out.println(longAdder.doubleValue());

        //doubleValue
        System.out.println(longAdder.doubleValue());

        //floatValue
        System.out.println(longAdder.floatValue());

        //increment
        longAdder.increment();
        System.out.println(longAdder.floatValue());

        //intValue
        System.out.println(longAdder.intValue());

        //longValue
        System.out.println(longAdder.longValue());

        //reset
        longAdder.reset();
        System.out.println(longAdder.longValue());

        //sum
        System.out.println(longAdder.sum());

        //sumThenReset
        System.out.println(longAdder.sumThenReset());

    }
}
