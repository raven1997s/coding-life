package com.raven.multithreaded.concurrentutil.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @PackageName: com.raven.multithreaded.concurrentutil
 * @ClassName: ConditionWait
 * @Blame: raven
 * @Date: 2021-08-31 9:01
 * @Description: condition使用demo 通过await阻塞线程
 */
public class ConditionWait implements Runnable {

    private Lock lock;

    private Condition condition;

    public ConditionWait(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            try {
                // condition await 方法 阻塞线程(类似于Object的wait方法，实现不同)
                // 他们都会做俩件事
                // 1.将当前线程阻塞加入等待队列
                // 2.释放锁
                // object 的wait方法 调用底层的native方法实现
                // condition 的await通过juc实现
                System.out.println("condition await before .....");
                condition.await();
                System.out.println("condition await after .....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
    }
}
