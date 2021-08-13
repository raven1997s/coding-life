package com.raven.multithreaded.interruptthread;

import java.util.concurrent.TimeUnit;

/**
 * @PackageName: com.raven.multithreaded.interruptthread
 * @ClassName: InterruptThreadTest
 * @Blame: raven
 * @Date: 2021-08-13 18:27
 * @Description: 线程的中断
 */
public class InterruptThreadTest {
    private static int i;
    public static void main(String[] args) throws InterruptedException {
        Thread interruptThread = new Thread(() -> {
            // isInterrupted 默认是false 默认是为中断
            while (!Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("i:" + i);
        }, "InterruptThread");
        interruptThread.start();
        TimeUnit.SECONDS.sleep(1);
        // 把isInterrupted设置为true
        // 底层是通过os系统设置 0，1来改变线程的中断状态，中断线程
        interruptThread.interrupt();
    }
}
