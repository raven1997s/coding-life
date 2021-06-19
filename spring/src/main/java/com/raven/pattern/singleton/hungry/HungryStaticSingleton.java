package com.raven.pattern.singleton.hungry;

/**
 * @PackageName: com.raven.pattern.singleton.hungry
 * @ClassName: HungryStaticSingleton
 * @Blame: raven
 * @Date: 2021-06-19 21:00
 * @Description: 静态饿汉式单例模式
 */
public class HungryStaticSingleton {

    private HungryStaticSingleton() {
    }

    private static final HungryStaticSingleton hungryStaticSingleton;

    static {
        hungryStaticSingleton = new HungryStaticSingleton();
    }

    public static HungryStaticSingleton getInstance() {
        return hungryStaticSingleton;
    }
}
