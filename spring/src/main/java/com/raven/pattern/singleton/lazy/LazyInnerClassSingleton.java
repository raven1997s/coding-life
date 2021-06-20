package com.raven.pattern.singleton.lazy;

/**
 * @PackageName: com.raven.pattern.singleton.lazy
 * @ClassName: LazyInnerClassSingleton
 * @Blame: raven
 * @Date: 2021-06-19 22:31
 * @Description: 内部类的方式创建单例对象 jdk内部创建单例的方式
 */
public class LazyInnerClassSingleton {
    /**
     * 单例模式类被反射所破坏 故加此代码禁止通过反射创建单例对象
     */
    private LazyInnerClassSingleton() {
        if (LazyHolder.singleton != null){
            throw new RuntimeException("不能通过反射创建该实例对象");
        }
    }

    /**
     * 通过内部类的方式，当LazyInnerClassSingleton 的getInstance方法被调用后，
     * JVM会通过自动加载内部类LazyHolder ，然后创建实例对象
     * 最优的方案！
     * @return
     */
    public static LazyInnerClassSingleton getInstance() {
        return LazyHolder.singleton;
    }

    private static class LazyHolder {
        private static final LazyInnerClassSingleton singleton = new LazyInnerClassSingleton();
    }
}
