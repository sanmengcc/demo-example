package com.sanmengcc.example.thread.aqs.atomic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @ClassNameAtomicReferenceFieldUpdaterTest
 * @Description
 * @Author sanmengc
 * @Date2020/7/6 19:23
 * @Version V1.0
 **/
public class AtomicReferenceFieldUpdaterTest {

    public static void main(String[] args) {

        //构造函数
        AtomicReferenceFieldUpdater<Person, String> updater = AtomicReferenceFieldUpdater.newUpdater(Person.class, String.class, "name");

        Person person = new Person(1001, "p1");
        //accumulateAndGet
        /*
         * 1.先执行BinaryOperator函数
         * 2.再get
         */
        System.out.println(updater.accumulateAndGet(person, "p1", (x, y) -> x + y));

        //compareAndSet
        /*
         * 1.先对比
         * 2.成功则更新指定数据
         */
        System.out.println(updater.compareAndSet(person, "p1p1", "p2"));

        //get
        System.out.println(updater.get(person));

        //getAndAccumulate
        /*
         * 1.先get
         * 2.再执行BinaryOperator函数
         */
        System.out.println(updater.getAndAccumulate(person, "p3", (x, y) -> x + y));

        //getAndSet
        /*
         * 1.先get
         * 2.再set指定数据
         */
        System.out.println(updater.getAndSet(person,"p4"));

        //getAndUpdate
        /*
         * 1.先get
         * 2.再执行UnaryOperator操作函数
         */
        System.out.println(updater.getAndUpdate(person, x -> x + "p5"));

        //lazySet
        updater.lazySet(person, "p6");

        //set
        updater.set(person, "p7");

        //updateAndGet
        /*
         * 1.先执行UnaryOperator操作函数
         * 2.再get
         */
        System.out.println(updater.updateAndGet(person, x -> x + "p8"));


        //weakCompareAndSet
        System.out.println(updater.weakCompareAndSet(person, "p8", "p9"));

    }
}

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
class Person {
    public volatile Integer no;
    public volatile String name;
}
