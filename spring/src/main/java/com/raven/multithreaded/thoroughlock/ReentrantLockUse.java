package com.raven.multithreaded.thoroughlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @PackageName: com.raven.multithreaded.thoroughlock
 * @ClassName: RentrantLock
 * @Blame: raven
 * @Date: 2021-08-24 21:08
 * @Description:
 */
public class ReentrantLockUse {

    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        System.out.println("do sth");
        lock.unlock();
    }
}
