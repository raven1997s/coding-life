package com.raven.multithreaded.concurrentutil.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @PackageName: com.raven.multithreaded.concurrentutil.countdownlatch
 * @ClassName: CountDownLatchTest
 * @Blame: raven
 * @Date: 2021-09-01 14:00
 * @Description: countdownlatch减数计数器 await阻塞到countDown计数为0后执行后续逻辑
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        // 定义一个countdownlatch计数器
        CountDownLatch countDownLatch = new CountDownLatch(3);
        new Thread(() -> {
            // countdownlatch 计数-1
            countDownLatch.countDown();
            System.out.println("thread 1");
        }).start();

        new Thread(() -> {
            // countdownlatch 计数-1
            countDownLatch.countDown();
            System.out.println("thread 2");
        }).start();

        new Thread(() -> {
            // countdownlatch 计数-1
            countDownLatch.countDown();
            System.out.println("thread 3");
        }).start();

        // 阻塞线程 当countdownlatch state计数减少到0 执行await后的逻辑
        countDownLatch.await();
        System.out.println("countdownlatch state 为0，执行业务逻辑");
    }
}

