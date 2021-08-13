package com.raven.multithreaded.interruptthread;

import java.util.concurrent.TimeUnit;

/**
 * @PackageName: com.raven.multithreaded.interruptthread
 * @ClassName: ThreadResetTest
 * @Blame: raven
 * @Date: 2021-08-13 18:37
 * @Description: 线程的复位
 */
public class ThreadResetTest {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            while (true){
                // 默认为false status = 0
                if (Thread.currentThread().isInterrupted()){
                    System.out.println("before:" + Thread.currentThread().isInterrupted());
                    // 复位 回到初始状态
                    Thread.interrupted();
                    System.out.println("after:" + Thread.currentThread().isInterrupted());
                }
            }
        });
        thread.start();

        TimeUnit.SECONDS.sleep(1);
        // 将中断标识改为 true
        thread.interrupt();
    }
}
