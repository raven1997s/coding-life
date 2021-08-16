package com.raven.multithreaded.memoryvisibility;

/**
 * @PackageName: com.raven.multithreaded.memoryvisibility
 * @ClassName: JoinSequenceTest
 * @Blame: raven
 * @Date: 2021-08-16 21:48
 * @Description: 通过join 确保多个线程顺序执行
 */
public class JoinSequenceTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            System.out.println("thread 1 sth");
        });
        Thread thread2 = new Thread(() -> {
            System.out.println("thread 2 sth");
        });
        Thread thread3 = new Thread(() -> {
            System.out.println("thread 3 sth");
        });

        // 通过join确保多个线程间顺序执行的原理就是建立了 happens- before原则
        // 从而使程序 顺序输出 thread 1  thread 2  thread 3
        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        thread3.start();
    }
}
