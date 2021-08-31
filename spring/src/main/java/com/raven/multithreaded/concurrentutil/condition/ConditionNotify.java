package com.raven.multithreaded.concurrentutil.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @PackageName: com.raven.multithreaded.concurrentutil.condition
 * @ClassName: ConditionNotify
 * @Blame: raven
 * @Date: 2021-08-31 9:02
 * @Description: condition使用demo 通过signal 或signalAll唤醒线程
 */
public class ConditionNotify implements Runnable {
    private Lock lock;

    private Condition condition;

    public ConditionNotify(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        try {
            lock.lock();

            // condition的signal or signalAll方法唤醒被阻塞的线程(类似于Object的notify or notifyAll)
            // object的notify 基于jvm底层指令实现
            // condition的signal通过juc实现
            System.out.println("condition signal before ......");
            condition.signal();
            System.out.println("condition signal after ......");
        } finally {
            lock.unlock();
        }
    }
}
