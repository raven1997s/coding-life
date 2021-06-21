package com.raven.pattern.singleton.register;

/**
 * @PackageName: com.raven.pattern.singleton.register
 * @ClassName: ContainerSingletonTest
 * @Blame: raven
 * @Date: 2021-06-21 21:36
 * @Description:
 */
public class ContainerSingletonTest {
    public static void main(String[] args) {
        new Thread(() -> {
            Object bean1 = ContainerSingleton.getBean("com.raven.pattern.singleton.register.TestEntity");
            Object bean2 = ContainerSingleton.getBean("com.raven.pattern.singleton.register.TestEntity");
            Object bean3 = ContainerSingleton.getBean("com.raven.pattern.singleton.register.TestEntity");
            System.out.println(Thread.currentThread().getName() + ":" + bean1);
            System.out.println(Thread.currentThread().getName() + ":" + bean2);
            System.out.println(Thread.currentThread().getName() + ":" + bean3);
        }).start();

        new Thread(() -> {
            Object bean4 = ContainerSingleton.getBean("com.raven.pattern.singleton.register.TestEntity");
            Object bean5 = ContainerSingleton.getBean("com.raven.pattern.singleton.register.TestEntity");
            Object bean6 = ContainerSingleton.getBean("com.raven.pattern.singleton.register.TestEntity");
            System.out.println(Thread.currentThread().getName() + ":" + bean4);
            System.out.println(Thread.currentThread().getName() + ":" + bean5);
            System.out.println(Thread.currentThread().getName() + ":" + bean6);
        }).start();
    }
}
