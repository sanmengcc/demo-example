package com.sanmengcc.example.thread.aqs.atomic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.LockSupport;

/**
 * @ClassNameAtomicStampReference
 * @Description
 * @Author sanmengcc
 * @Date2020/7/6 14:34
 * @Version V1.0
 **/
public class AtomicStampReferenceTest {

    public static void main(String[] args) {
        // 创建AtomicStampedReference对象，持有Good对象的引用，初始为null，版本为0
        AtomicStampedReference<Good> asr = new AtomicStampedReference<>(null, 0);
        System.out.println(asr.getReference());

        //attemptStamp
        /*
         * 1.expectedReference期望值 == 实际值则更新版本信息
         */
        AtomicStampedReference<String> reference = new AtomicStampedReference<>("a1", 0);
        reference.attemptStamp("a1", 1);
        System.out.println(reference.getReference());
        System.out.println(reference.getStamp());
        reference.attemptStamp("a1", reference.getStamp() + 1);
        System.out.println(reference.getReference());
        System.out.println(reference.getStamp());

        //compareAndSet
        /*
         * 1.expectedReference期望值 == 实际值则更新版本信息
         * 2.expectedStamp期望版本号 == 实际版本号
         * 3.均满足则执行set新值操作
         */
        reference.compareAndSet("a1", "a2", 2, 3);
        System.out.println(reference.getReference());
        System.out.println(reference.getStamp());

        //get 返回该引用和该标志的当前值
        reference.compareAndSet("a2", "a4", 3, 4);
        int[] stampHolder = new int[1];
        System.out.println(reference.get(stampHolder));
        System.out.println(stampHolder[0]);

        //getReference 获取当前引用的值
        System.out.println(reference.getReference());

        //getStamp 获取当前的版本号
        System.out.println(reference.getStamp());

        //set
        reference.set("a5", 5);
        System.out.println(reference.getReference());
        System.out.println(reference.getStamp());

        //weakCompareAndSet
        System.out.println(reference.weakCompareAndSet("a5", "a6", 5, 6));

        testStamp();

    }

    private static void testStamp() {
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1, 1);

        new Thread(()->{
            int[] stampHolder = new int[1];
            int value = atomicStampedReference.get(stampHolder);
            int stamp = stampHolder[0];
            System.out.println("AtomicThread-1 get value: " + value + ", stamp: " + stamp);

            // 阻塞1s
            LockSupport.parkNanos(1000000000L);

            //执行更新操作
            //预期操作 reference: 1->3 stamp: 1->2
            if (atomicStampedReference.compareAndSet(value, 3, stamp, stamp + 1)) {
                System.out.println("AtomicThread-1 set from " + value + " to 3");
            } else {
                System.out.println("AtomicThread-1 set fail!");
            }
        },"AtomicThread-1").start();

        new Thread(()->{
            int[] stampHolder = new int[1];
            int value = atomicStampedReference.get(stampHolder);
            int stamp = stampHolder[0];
            System.out.println("AtomicThread-1 read value: " + value + ", stamp: " + stamp);
            if (atomicStampedReference.compareAndSet(value, 2, stamp, stamp + 1)) {
                System.out.println("AtomicThread-1 set from " + value + " to 2");

                value = atomicStampedReference.get(stampHolder);
                stamp = stampHolder[0];
                System.out.println("AtomicThread-1 get value: " + value + ", stamp: " + stamp);
                //预期操作 reference: 3->1 stamp: 2->3
                if (atomicStampedReference.compareAndSet(value, 1, stamp, stamp + 1)) {
                    System.out.println("AtomicThread-1 set from " + value + " to 1");
                }
            }
        },"AtomicThread-2").start();
    }

}
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Good{

    private String goodName;
}
