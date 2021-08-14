package com.raven.multithreaded.synchronizedtheory.basicuse;

/**
 * @PackageName: com.raven.multithreaded.synchronizedtheory
 * @ClassName: BasecUseTest
 * @Blame: raven
 * @Date: 2021-08-14 16:08
 * @Description: 模拟使用不同方式的锁
 */
public class BasicUseTest {

    public static void main(String[] args) {
        // 模拟使用synchronized修饰静态方法的锁
//        useStaticMethodLock();
        Object lock = new Object();
        BasicUse basicUse1 = new BasicUse(lock);
        BasicUse basicUse2 = new BasicUse(lock);

        // 模拟使用synchronized修饰非静态方法的锁
//        useNonStaticMethodLock(basicUse1, basicUse2);

        // 模拟使用synchronized修饰代码块 -> 锁对象为BasicUse对象的class对象
//        useCodeBlockMethodLock(basicUse1, basicUse2);

        // 模拟使用synchronized修饰代码块 -> 锁对象为传入的Object对象，不同实例不同线程间仍然线程安全
        useCodeLockMethodLock(basicUse1, basicUse2);


    }

    private static void useCodeLockMethodLock(BasicUse basicUse1, BasicUse basicUse2) {
        new Thread(() -> {
            basicUse1.codeBlockLock();
        }).start();
        new Thread(() -> {
            basicUse2.codeBlockLock();
        }).start();
    }

    private static void useCodeBlockMethodLock(BasicUse basicUse1, BasicUse basicUse2) {
        new Thread(() -> {
            basicUse1.codeBlockClass();
        }).start();
        new Thread(() -> {
            basicUse2.codeBlockClass();
        }).start();
    }

    private static void useNonStaticMethodLock(BasicUse basicUse1, BasicUse basicUse2) {
        new Thread(() -> {
            basicUse1.nonStaticMethod();
        }).start();
        new Thread(() -> {
            basicUse2.nonStaticMethod();
        }).start();
    }

    public static void useStaticMethodLock() {
        new Thread(() -> {
            BasicUse.staticMethod();
        }).start();
        new Thread(() -> {
            BasicUse.staticMethod();
        }).start();
    }
}
