package com.sanmengcc.example.thread.aqs.atomic.info.readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

/**
 * @ClassNameReentrantReadWriteLockTest1
 * @Description
 * @Author huhd
 * @Date2020/7/13 20:54
 * @Version V1.0
 **/
public class ReentrantReadWriteLockTest1 {


    public static void main(String[] args) {
        DataRecourse dataRecourseFree = new DataRecourse();
        IntStream.range(0,10)
                .forEach(i->{
                    new Thread(() -> {
                        dataRecourseFree.put(String.valueOf(i), String.valueOf(i));
                    }, String.valueOf(i)).start();
                });
        IntStream.range(0,10)
                .forEach(i->{
                    new Thread(() -> {
                        dataRecourseFree.get(String.valueOf(i));

                    }, String.valueOf(i)).start();
                });
    }
}

class DataRecourseFree{
    private volatile Map<String ,String> dataMap = new HashMap<>();

    public void put(String key, String value) {
        System.out.println(Thread.currentThread().getName() + " 正在执行写入操作.......["+key+":"+value+"]");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dataMap.put(key, value);
        System.out.println(Thread.currentThread().getName() + " 正在执行写入操作执行完毕.......");
    }

    public String get(String key) {
        System.out.println(Thread.currentThread().getName() + " 正在执行读取操作.......");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 正在执行读取操作执行完毕.......:["+key+":"+dataMap.get(key)+"]");
        return dataMap.get(key);
    }
}


class DataRecourse{

    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private volatile Map<String ,String> dataMap = new HashMap<>();

    public void put(String key, String value) {
        try {
            lock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + " 正在执行写入操作.......["+key+":"+value+"]");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            dataMap.put(key, value);
            System.out.println(Thread.currentThread().getName() + " 正在执行写入操作执行完毕.......");
        }finally {
            lock.writeLock().unlock();
        }
    }

    public String get(String key) {
        try {
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + " 正在执行读取操作.......");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 正在执行读取操作执行完毕.......:["+key+":"+dataMap.get(key)+"]");
            return dataMap.get(key);
        }finally {
            lock.readLock().unlock();
        }
    }
}

