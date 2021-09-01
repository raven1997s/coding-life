package com.raven.multithreaded.concurrentutil.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @PackageName: com.raven.multithreaded.concurrentutil.countdownlatch
 * @ClassName: CountDownLatchTest2
 * @Blame: raven
 * @Date: 2021-09-01 14:26
 * @Description:模拟并发，使用await阻塞多个线程，当countDown计数为0后统一执行wait后逻辑
 */
public class CountDownLatchTest2 {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    // 阻塞多个线程
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread:" +Thread.currentThread().getName());
            }).start();
        }

        // 释放阻塞
        countDownLatch.countDown();

    }
}
