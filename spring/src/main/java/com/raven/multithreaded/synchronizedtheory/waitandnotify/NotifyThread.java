package com.raven.multithreaded.synchronizedtheory.waitandnotify;

/**
 * @PackageName: com.raven.multithreaded.synchronizedtheory.waitandnotify
 * @ClassName: Thread1
 * @Blame: raven
 * @Date: 2021-08-14 16:30
 * @Description:
 */
public class NotifyThread extends Thread {

    private Object lock;

    public NotifyThread(Object object) {
        this.lock = object;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("notify before:" + Thread.currentThread().getName() + " running");
            lock.notify();
            System.out.println("notify after:" + Thread.currentThread().getName() + " running");
        }
    }
}
