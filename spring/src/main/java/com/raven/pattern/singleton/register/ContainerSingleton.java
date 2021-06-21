package com.raven.pattern.singleton.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @PackageName: com.raven.pattern.singleton.register
 * @ClassName: ContainerSingleton
 * @Blame: raven
 * @Date: 2021-06-21 21:24
 * @Description: 容器式单例模式
 */
public class ContainerSingleton {

    private ContainerSingleton() {
    }

    private static Map<String, Object> ioc = new ConcurrentHashMap<>();

    public static Object getBean(String className) {
        synchronized (ContainerSingleton.class) {
            if (!(ioc.containsKey(className))) {
                Object obj = null;
                try {
                    obj = Class.forName(className);
                    ioc.put(className, obj);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return obj;
            }
        }
        return ioc.get(className);
    }
}
