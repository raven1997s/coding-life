package com.raven.multithreaded.thoroughlock;

import lombok.Data;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @PackageName: com.raven.multithreaded.thoroughlock
 * @ClassName: Demo
 * @Blame: raven
 * @Date: 2021-08-19 21:38
 * @Description: 使用Lock锁
 */
@Data
public class UseLock {

    static ReentrantLock lock = new ReentrantLock();
    static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static int num = 0;

    private static int count = 10;

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                lock.lock();
                for (int i1 = 0; i1 < 1000; i1++) {
                    num ++;
                }
//                lock.unlock();
            }).start();
        }
        Thread.yield();
        System.out.println(num);
    }
}
