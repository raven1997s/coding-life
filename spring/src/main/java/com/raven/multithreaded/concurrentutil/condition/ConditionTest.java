package com.raven.multithreaded.concurrentutil.condition;

import java.util.concurrent.locks.*;

/**
 * @PackageName: com.raven.multithreaded.concurrentutil.condition
 * @ClassName: ConditionTest
 * @Blame: raven
 * @Date: 2021-08-31 9:23
 * @Description:
 */
public class ConditionTest {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(new ConditionWait(lock,condition)).start();
        new Thread(new ConditionNotify(lock,condition)).start();
    }
}
