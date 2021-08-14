package com.raven.multithreaded.synchronizedtheory.waitandnotify;

/**
 * @PackageName: com.raven.multithreaded.synchronizedtheory.waitandnotify
 * @ClassName: WaitAndNotifyTest
 * @Blame: raven
 * @Date: 2021-08-14 16:35
 * @Description: 模拟线程阻塞与唤醒
 */
public class WaitAndNotifyTest {

    public static void main(String[] args) {
        Object lock = new Object();
        WaitThread waitThread = new WaitThread(lock);
        waitThread.start();
        NotifyThread notifyThread = new NotifyThread(lock);
        // notify线程和wait线程必须使用同一把锁，notify线程才能唤醒wait线程
//        Object lock2 = new Object();
//        NotifyThread notifyThread = new NotifyThread(lock2);
        notifyThread.start();
    }
}
