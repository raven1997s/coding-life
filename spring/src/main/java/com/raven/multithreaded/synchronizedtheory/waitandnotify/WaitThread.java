package com.raven.multithreaded.synchronizedtheory.waitandnotify;

/**
 * @PackageName: com.raven.multithreaded.synchronizedtheory.waitandnotify
 * @ClassName: Thread1
 * @Blame: raven
 * @Date: 2021-08-14 16:30
 * @Description:
 */
public class WaitThread extends Thread {

    private Object lock;

    public WaitThread(Object object) {
        this.lock = object;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("wait before:" + Thread.currentThread().getName() + " running");
            try {
                // 会做俩件事
                // 1.将当前线程阻塞加入等待队列
                // 2.释放锁
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("wait after:" + Thread.currentThread().getName() + " running");
        }
    }
}
