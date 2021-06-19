package com.raven.pattern.singleton.lazy;

/**
 * @PackageName: com.raven.pattern.singleton.lazy
 * @ClassName: LazySimpleSingleton
 * @Blame: raven
 * @Date: 2021-06-19 22:06
 * @Description: 简单的懒汉式单例模式
 */
public class LazySimpleSingleton {

    private LazySimpleSingleton() {
    }

    private static LazySimpleSingleton lazySimpleSingleton = null;

    /**
     * 虽然jdk1.6版本对于synchronized进行了优化，但是在方法上直接加synchronized依旧不是最理想的懒汉式单例模式
     *
     * @return
     */
    public static synchronized LazySimpleSingleton getInstance() {
        if (lazySimpleSingleton == null) {
            lazySimpleSingleton = new LazySimpleSingleton();
        }
        return lazySimpleSingleton;
    }
}
