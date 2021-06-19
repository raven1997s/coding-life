package com.raven.pattern.singleton.lazy;

/**
 * @PackageName: com.raven.pattern.singleton.lazy
 * @ClassName: LazyDoubleCheckSingleton
 * @Blame: raven
 * @Date: 2021-06-19 22:13
 * @Description:双重检查锁懒汉式单例模式
 */
public class LazyDoubleCheckSingleton {

    private LazyDoubleCheckSingleton(){};
    /**
     * 加volatile关键字是为了确保多线程环境下创建对象时内存可见
     * 因为创建对象时 jvm会进行指令重排序，new LazyDoubleCheckSingleton 是多步操作 会有线程安全问题
     */
    private static volatile LazyDoubleCheckSingleton lazyDoubleCheckSingleton = null;

    /**
     * 将synchronized 锁范围缩小
     *
     * @return
     */
    public static LazyDoubleCheckSingleton getInstance() {
        if (lazyDoubleCheckSingleton == null) {
            synchronized (LazyDoubleCheckSingleton.class) {
                // 双重判断的原因是因为，假设有俩个线程A、B 如果仅仅加锁以及没有第二个if判断，
                // 线程A、B可以同时进入方法并尝试获取LazyDoubleCheckSingleton锁，
                // 假设A线程获取到LazyDoubleCheckSingleton锁，B线程就会进入等待状态，
                // 当A线程创建完对象 但getInstance方法没有return前，B线程也会获取到锁对象，并且创建对象，从而破坏单例
                if (lazyDoubleCheckSingleton == null) {
                    lazyDoubleCheckSingleton = new LazyDoubleCheckSingleton();
                }
            }
        }
        return lazyDoubleCheckSingleton;
    }
}
