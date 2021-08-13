package com.raven.multithreaded.interruptthread;

import java.util.concurrent.TimeUnit;

/**
 * @PackageName: com.raven.multithreaded.interruptthread
 * @ClassName: ExceptionThreadTest
 * @Blame: raven
 * @Date: 2021-08-13 18:49
 * @Description: 中断一个处于阻塞状态的线程
 */
public class ExceptionThreadTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()){
                try {
                    //中断一个处于阻塞状态的线程 会抛出 InterruptedException异常
                    TimeUnit.SECONDS.sleep(1);
//                    System.out.println("demo");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }

//            System.out.println("ExceptionThreadTest");
        });
        thread.start();

        TimeUnit.SECONDS.sleep(1);
        // 将中断状态改为true
        thread.interrupt();
        System.out.println(thread.isInterrupted());
    }
}
