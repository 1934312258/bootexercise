package com.kevin.javaDemo.concurrent.lock;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {

    public static void main(String[] args) throws InterruptedException {

        /** 读写锁默认非公平锁
         *  读写锁对于锁标志的处理方式如下：
         *      读锁与写锁的锁标记位均为state，state为int类型，高16位代表读锁，低16位代表写锁
         *      获取锁时首先判断state值是否为零，不为零则代表有线程已经获取到读锁或者写锁，获取写锁获取锁次数
         *      如果次数为零，则代表目前有线程获取读锁，如当前线程要获取读锁则判断当前读锁获取次数是否达到上限，未达到
         *      通过cas修改state状态，达到则直接报错，如当前线程获取写锁，则判断当前线程与获取写锁线程是否一致，不一致直接返回false，一致则判断
         *      重入写锁次数是否达到上限，如果未达到则通过cas修改state值，如果达到则报异常
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         * */

        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        readLock.lock();
        writeLock.lock();

        DelayQueue queue = new DelayQueue();
        queue.take();
    }
}
