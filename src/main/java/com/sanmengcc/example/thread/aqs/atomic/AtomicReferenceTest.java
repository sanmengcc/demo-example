package com.sanmengcc.example.thread.aqs.atomic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BinaryOperator;

/**
 * @ClassNameAtomicReferenceTest
 * @Description
 * @Author sanmengcc
 * @Date2020/7/5 22:10
 * @Version V1.0
 **/
public class AtomicReferenceTest {

    public static void main(String[] args) {

        //构造函数
        AtomicReference a1 = new AtomicReference();
        AtomicReference<String> a2 = new AtomicReference<>("x");

        //accumulateAndGet
        /*
         * 1.先执行BinaryOperator函数
         * 2.get操作
         */
        Student s1 = new Student("s1", 1);
        Student s2 = new Student("s2", 2);
        //accumulateAndGet
        AtomicReference<Student> reference = new AtomicReference<>(s1);
        Student student = reference.accumulateAndGet(s2, (x, y) -> {
            if (x.getAge() > y.getAge()) return x;
            else return y;
        });
        System.out.println(student);

        //compareAndSet
        /*
         * 1.如果当前值 == 预期值，则以原子方式将该值设置为给定的更新值
         * 2.对于String变量来说,必须是对象相同才视为相同,而不是字符串的内容相同就可以相同
         */
        boolean compareAndSet = reference.compareAndSet(s1, s2);
        System.out.println(compareAndSet);
        System.out.println(reference.get());

        //get
        System.out.println(reference.get());

        //getAndAccumulate
        /*
         * 1.先执行get操作
         * 2.然后再执行BinaryOperator函数
         */
        System.out.println(reference.getAndAccumulate(s1,(x,y)->{
            if (x.getAge() < y.getAge()) return x;
            else return y;
        }));
        System.out.println(reference.get());

        //getAndSet
        /*
         * 1.先执行get然后set一个新的对象
         */
        reference.set(s2);
        System.out.println(reference.get());

        //getAndUpdate
        /*
         * 1.先执行get
         * 2.再执行UnaryOperator函数操作
         */
        System.out.println(reference.getAndUpdate((x) -> {
            x.setAge(50);
            return x;
        }));
        System.out.println(reference.get());

        //lazySet
        reference.lazySet(new Student("s3", 20));
        System.out.println(reference);

        //set
        reference.set(new Student("s4", 21));
        System.out.println(reference.get());

        //updateAndGet
        /*
         * 1.先执行UnaryOperator函数操作
         * 2.再执行get
         */
        System.out.println(reference.updateAndGet((x) -> {
            x.setAge(22);
            return x;
        }));

        //weakCompareAndSet
        reference.weakCompareAndSet(s1, s2);



    }




}

class AtomicReferenceDemo {

    public static void main(String[] args) {
        AtomicReferenceDemo demo = new AtomicReferenceDemo(new Reference(1, 1));
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                long millis = System.currentTimeMillis();
                demo.atomic(new Reference(millis, millis));
            }).start();
        }
    }

    private Reference reference;

    private AtomicReference<Reference> atomicReference;

    /**
     * 构建器中初始化AtomicReference
     *
     * @param reference
     */
    public AtomicReferenceDemo(Reference reference) {
        this.reference = reference;
        this.atomicReference = new AtomicReference<>(reference);
    }

    public void atomic(Reference reference) {
        Reference referenceOld;
        Reference referenceNew;

        long sequence;
        long timestamp;

        while (true) {
            referenceOld = this.atomicReference.get();
            sequence = referenceOld.getSequence();
            sequence++;
            timestamp = System.currentTimeMillis();

            referenceNew = new Reference(sequence, timestamp);
            /**
             * 比较交换
             */
            if (this.atomicReference.compareAndSet(referenceOld, referenceNew)) {
                reference.setSequence(sequence);
                reference.setTimestamp(timestamp);
                break;
            }
        }
        this.reference = reference;
        System.out.println("reference = " + this.reference);
    }
}


@Data
@AllArgsConstructor
class Reference {
    /**
     * 序列
     */
    private long sequence;
    /**
     * 时间戳
     */
    private long timestamp;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Student{
    private String name;

    private Integer age;
}
