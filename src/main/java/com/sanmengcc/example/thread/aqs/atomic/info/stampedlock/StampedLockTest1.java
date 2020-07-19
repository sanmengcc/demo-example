package com.sanmengcc.example.thread.aqs.atomic.info.stampedlock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @ClassNameStampedLockTest1
 * @Description
 * @Author sanmengcc
 * @Date2020/7/18 17:10
 * @Version V1.0
 **/
public class StampedLockTest1 {

    private static StampedLock lock = new StampedLock();

    private final static List<Long> DATA = new ArrayList<>();

    public static void main(String[] args) {
        Runnable read = () -> {
            for (; ; ) {
                read();
            }
        };
        Runnable write = () -> {
            for (; ; ) {
                write();
            }
        };
        IntStream.range(0, 1).boxed().forEach(i -> new Thread(write).start());
        IntStream.range(0, 200).boxed().forEach(i -> new Thread(read).start());
    }

    public static void write() {
        long stamped = -1;
        try {
            stamped = lock.writeLock();
            DATA.add(System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlockWrite(stamped);
        }
    }

    public static void read() {
        long stamped = -1;
        try{
            stamped = lock.readLock();
            Optional.of(DATA.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining("#", "R-", "")))
                    .ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlockRead(stamped);
        }
    }
}
