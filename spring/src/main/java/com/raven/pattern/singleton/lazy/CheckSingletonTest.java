package com.raven.pattern.singleton.lazy;

/**
 * @PackageName: com.raven.pattern.singleton.lazy
 * @ClassName: CheckSingletonTest
 * @Blame: raven
 * @Date: 2021-06-19 22:33
 * @Description:
 */
public class CheckSingletonTest {

    public static void main(String[] args) {
        // 可通过debug的方式 看到多线程中对象是否实现单例式创建
        Thread thread1 = new Thread(new ExecutorSingleton());
        Thread thread2 = new Thread(new ExecutorSingleton());

        thread1.start();
        thread2.start();
        System.out.println("end");

    }
}
