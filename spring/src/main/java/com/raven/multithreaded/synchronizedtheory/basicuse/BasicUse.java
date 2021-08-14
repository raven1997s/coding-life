package com.raven.multithreaded.synchronizedtheory.basicuse;

/**
 * @PackageName: com.raven.multithreaded.synchronizedtheory
 * @ClassName: BasicUse
 * @Blame: raven
 * @Date: 2021-08-14 15:46
 * @Description: synchronized 基本使用
 * <p>
 * 2种表现形式
 * 2种作用范围 (对象锁or类锁)区别是 是否挂对象跨线程被保护
 * 控制锁的范围由对象的生命周期而决定！！！
 * 1.修饰实例方法
 * 2.修饰静态方法
 * 3.修饰代码块
 */
public class BasicUse {
    private final Object lock;

    public BasicUse(Object lock) {
        this.lock = lock;
    }
    /**
     * 修饰静态方法
     * 锁对象是BasicUse.class(对象锁)
     * 是类锁，不同实例访问该方法会出现锁竞争
     * 锁的范围较大(整个方法代码都会上锁)
     */
    public synchronized static void staticMethod() {
        System.out.println("synchronized修饰静态方法");
    }

    /**
     * 修饰费静态方法
     * 锁对象是BasicUse对象
     * 是对象锁，不同BasicUse实例间不会出现锁竞争
     * 锁的范围较大(整个方法代码都会上锁)
     */
    public synchronized void nonStaticMethod() {
        System.out.println("synchronized修饰非静态方法");
    }

    /**
     * 修饰代码块
     * 锁对象是lock对象，不同BasicUse实例传入同一个lock对象时会出现锁竞争
     * 锁的范围较小
     */
    public void codeBlockLock() {
        System.out.println("before sth..");
        synchronized (lock) {
            System.out.println("锁对象为 lock ");
        }
        System.out.println("after sth..");
    }

    /**
     * 修饰代码块
     * 锁对象是BasicUse的class类对象，会出现锁竞争
     * 锁的范围较小
     */
    public void codeBlockClass() {
        System.out.println("before sth..");
        synchronized (BasicUse.class) {
            System.out.println("锁对象为 BasicUse的class类对象 ");
        }
        System.out.println("after sth..");
    }
    /**
     * 修饰代码块
     * 锁对象是BasicUse对象，不同的BasicUse实例间不会出现锁竞争
     */
    public void codeBlockThis(){
        System.out.println("before sth..");
        synchronized (this) {
            System.out.println("锁对象为 BasicUse对象 ");
        }
        System.out.println("after sth..");
    }

}
