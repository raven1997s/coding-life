package com.raven.pattern.singleton.register;

/**
 * @PackageName: com.raven.pattern.singleton.register
 * @ClassName: ThreadLocalSingleton
 * @Blame: raven
 * @Date: 2021-06-21 21:43
 * @Description: ThreadLocal线程间单例 可用于实现ORM动态切换数据源
 */
public class ThreadLocalSingleton {
    private ThreadLocalSingleton() {
    }

    private static final ThreadLocal<ThreadLocalSingleton> threadLocalInstance = ThreadLocal.withInitial(ThreadLocalSingleton::new);

    public static ThreadLocalSingleton getInstance() {
        return threadLocalInstance.get();
    }
}
