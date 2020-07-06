package com.sanmengcc.example.thread.aqs.atomic;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @ClassNameAtomicLongFieldUpdaterTest
 * @Description
 * @Author sanmengcc
 * @Date2020/7/6 19:12
 * @Version V1.0
 **/
public class AtomicLongFieldUpdaterTest {

    public static void main(String[] args) {
        //构造函数
        LongBalance balance = new LongBalance();
        AtomicIntegerFieldUpdater<LongBalance> updater = AtomicIntegerFieldUpdater.newUpdater(LongBalance.class, "balance");

        //addAndGet
        /*
         * 1.先add指定数值
         * 2.再get
         */
        System.out.println(updater.addAndGet(balance, 2));

        //compareAndSet
        /*
         * 1.先对比数值
         * 2.匹配则进行更新
         */
        System.out.println(updater.compareAndSet(balance, 1002, 10003));

        //decrementAndGet
        /*
         * 1.先执行自减1
         * 2.再get
         */
        System.out.println(updater.decrementAndGet(balance));

        //get
        System.out.println(updater.get(balance));

        //getAndAdd
        /*
         * 1.先get
         * 2.再add指定数值
         */
        System.out.println(updater.getAndAdd(balance, 10));

        //getAndDecrement
        /*
         * 1.先get
         * 2.再进行自减1
         */
        System.out.println(updater.getAndDecrement(balance));

        //getAndIncrement
        /*
         * 1.先get
         * 2.再进行自增1
         */
        System.out.println(updater.getAndIncrement(balance));

        //getAndSet
        /*
         * 1.先get
         * 2.再set指定数值
         */
        System.out.println(updater.getAndSet(balance, 30));

        //incrementAndGet
        /*
         * 1.先进行自增1
         * 2.再get
         */
        System.out.println(updater.incrementAndGet(balance));

        //lazySet
        updater.lazySet(balance, 1);

        //set
        updater.set(balance, 12);

        //weakCompareAndSet
        updater.weakCompareAndSet(balance, 20,21);


    }
}
@Data
@Getter
@ToString
class LongBalance{
    public volatile long balance = 1000;

}
