package com.raven.pattern.singleton.register;

/**
 * @PackageName: com.raven.pattern.singleton.register
 * @ClassName: ThreadLocalSingletonTest
 * @Blame: raven
 * @Date: 2021-06-21 21:47
 * @Description: threadLocal可实现线程间单例
 */
public class ThreadLocalSingletonTest {

    public static void main(String[] args) {
        new Thread(() -> {
            ThreadLocalSingleton bean1 = ThreadLocalSingleton.getInstance();
            ThreadLocalSingleton bean2 = ThreadLocalSingleton.getInstance();
            ThreadLocalSingleton bean3 = ThreadLocalSingleton.getInstance();
            System.out.println(Thread.currentThread().getName() + ":" + bean1);
            System.out.println(Thread.currentThread().getName() + ":" + bean2);
            System.out.println(Thread.currentThread().getName() + ":" + bean3);
        }).start();

        new Thread(() -> {
            ThreadLocalSingleton bean4 = ThreadLocalSingleton.getInstance();
            ThreadLocalSingleton bean5 = ThreadLocalSingleton.getInstance();
            ThreadLocalSingleton bean6 = ThreadLocalSingleton.getInstance();
            System.out.println(Thread.currentThread().getName() + ":" + bean4);
            System.out.println(Thread.currentThread().getName() + ":" + bean5);
            System.out.println(Thread.currentThread().getName() + ":" + bean6);
        }).start();
    }
}
