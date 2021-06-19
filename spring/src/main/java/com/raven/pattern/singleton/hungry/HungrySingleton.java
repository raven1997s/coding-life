package com.raven.pattern.singleton.hungry;

/**
 * @PackageName: com.raven.pattern.singleton.hungry
 * @ClassName: HungrySingleton
 * @Blame: raven
 * @Date: 2021-06-19 21:00
 * @Description: 饿汉式单例模式
 */
public class HungrySingleton {

    private HungrySingleton() {
    }

    private static final HungrySingleton hungrySingleton = new HungrySingleton();

    public static HungrySingleton getInstance() {
        return hungrySingleton;
    }
}
