package com.sanmengcc.example.thread.aqs.atomic.info.stampedlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * @ClassNameStampedLockTest
 * @Description
 * @Author sanmengcc
 * @Date2020/7/18 14:49
 * @Version V1.0
 **/
public class StampedLockTest {

    public static void main(String[] args) throws InterruptedException {
        //构造函数
        StampedLock stampedLock = new StampedLock();

        //writeLock
        /*
         * 1.获取写入锁
         * 2.获取失败则回阻塞当前线程、直到成功获取锁
         * 3.返回值：可以用来解锁或转换模式的戳记
         */
        stampedLock.writeLock();

        //tryWriteLock
        /*
         * 1.没有锁存在的情况下获取写入锁
         * 2.否则返回0
         * 3.可以设置等待超时时间
         * 4.返回值：可以用来解锁或转换模式的戳记
         */
        stampedLock.tryWriteLock();
        stampedLock.tryWriteLock(1, TimeUnit.MILLISECONDS);

        //writeLockInterruptibly
        /*
         * 1.获取写锁
         * 2.成功返回状态值，失败返回0，或抛出InterruptedException
         */
        stampedLock.writeLockInterruptibly();

        //readLock
        /*
         * 1.获取读取锁（悲观、非独占）
         * 2.获取不到则会陷入阻塞直到成功获取到读取锁
         */
        stampedLock.readLock();

        //可以立即获得锁，则获取读锁，否则返回0

        //tryReadLock
        /*
         * 1.立即获得读取锁
         * 2.否则返回0
         * 3.可以设置等待超时时间
         */
        stampedLock.tryReadLock();
        stampedLock.tryReadLock(1, TimeUnit.MILLISECONDS);

        //tryOptimisticRead
        /*
         * 1.获取乐观读取锁
         * 2.返回邮戳stamp
         */
        stampedLock.tryOptimisticRead();

        //validate
        /*
         * 1.验证邮戳（tryOptimisticRead方法调用前后）有没有写锁占用锁资源
         * 2.占用：返回false
         * 3.未占用：返回true
         * 4.stamp == 0:返回false
         */
        stampedLock.validate(10);

        //unlockWrite
        /*
         * 1.state和stamp匹配释放写入锁
         */
        stampedLock.unlockWrite(10);

        //unlockRead
        /*
         * 1.state和stamp匹配释放读取锁
         */
        stampedLock.unlockRead(10);

        //unlock
        /*
         * 1.state匹配stamp时,释放一个读锁或写锁
         */
        stampedLock.unlock(10);

        //tryConvertToWriteLock
        /*
         * 1.进行state匹配stamp
         * 2.stamp如果已经持有写锁，直接返回
         * 3.读模式，但是没有更多的读取者，并返回一个写锁stamp
         * 4.有一个乐观读锁，只在即时可用的前提下返回一个写锁stamp
         * 5.其他情况都返回0
         */
        stampedLock.tryConvertToWriteLock(10);

        //tryConvertToReadLock
        /*
         * 1.进行state匹配stamp
         * 2.stamp如果持有写锁，释放写锁，并持有读锁
         * 3.stamp如果表示持有读锁 ，返回该读锁
         * 4.有一个乐观读锁，只在即时可用的前提下返回一个读锁stamp
         * 5.其他情况都返回0，表示失败
         */
        stampedLock.tryConvertToReadLock(10);

        //tryConvertToOptimisticRead
        /*
         * 1.将其他锁转换为乐观锁
         * 2.stamp如果持有读或写锁，则直接释放读写锁
         * 3.stamp如果持有乐观锁，若乐观锁stamp有效，则返回观察者stamp
         */
        stampedLock.tryConvertToOptimisticRead(10);

        //tryUnlockWrite
        /*
         * 1.如果持有写锁，释放写锁
         * 2.可以用于发生错误后的恢复
         */
        stampedLock.tryUnlockWrite();

        //tryUnlockRead
        /*
         * 1.如果持有读锁，释放读锁
         * 2.可以用于发生错误后的恢复
         */
        stampedLock.tryUnlockRead();

        //isWriteLocked
        /*
         * 1.持有写锁返回true
         * 2.不持有返回false
         */
        System.out.println(stampedLock.isWriteLocked());

        //isReadLocked
        /*
         * 1.持有读锁返回true
         * 2.不持有返回false
         */
        System.out.println(stampedLock.isReadLocked());

        //getReadLockCount
        /*
         * 1.返回持有读锁的线程数
         */
        System.out.println(stampedLock.getReadLockCount());

        //asReadLock
        /*
         * 1.返回一个ReadLock视图
         */
        stampedLock.asReadLock();

        //asWriteLock
        /*
         * 1.返回一个WriteLock视图
         */
        stampedLock.asWriteLock();

        //asReadWriteLock
        /*
         * 1.返回一个ReadWriteLock视图
         */
        stampedLock.asReadWriteLock();


    }

}
