package com.sanmengcc.example.thread.aqs.atomic;

import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @ClassNameAtomicMarkableReferenceTest
 * @Description
 * @Author sanmengcc
 * @Date2020/7/6 20:03
 * @Version V1.0
 **/
public class AtomicMarkableReferenceTest {

    public static void main(String[] args) {
        //构造函数
        AtomicMarkableReference markableReference = new AtomicMarkableReference("A", true);

        //attemptMark
        /*
         * 1.以原子方式设置标记值为新的值
         * 2.当期望的引用值与当前引用值相同时，操作成功，返回true
         */
        markableReference.attemptMark("A", false);
        System.out.println(markableReference.getReference());

        //compareAndSet
        /*
         * 1.期望引用值 == 期望标记值
         * 2.新的引用值和新的标记值不同时等于当前值
         */
        markableReference.compareAndSet("A", "B", false, true);

        //get
        /*
         * 1.将mark写入arr
         * 2.返回对象
         */
        boolean[] arr = new boolean[1];
        System.out.println(markableReference.get(arr));

        //getReference
        System.out.println(markableReference.getReference());

        //isMarked
        System.out.println(markableReference.isMarked());

        //set
        /*
         * 1.对象引用不同
         * 2.mark不同
         * 3.执行更新操作
         */
        markableReference.set("B", true);

        //weakCompareAndSet
        markableReference.weakCompareAndSet("A", "B", false, true);

    }
}
