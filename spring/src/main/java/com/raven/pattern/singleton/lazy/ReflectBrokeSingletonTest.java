package com.raven.pattern.singleton.lazy;

import java.lang.reflect.Constructor;

/**
 * @PackageName: com.raven.pattern.singleton.lazy
 * @ClassName: ReflectBrokeSingletonTest
 * @Blame: raven
 * @Date: 2021-06-20 9:21
 * @Description: 模拟反射破坏单例问题
 */
public class ReflectBrokeSingletonTest {

    public static void main(String[] args) throws Exception {
        // 虽然我们通过代码的方式实现了单例设计，但当调用者不走寻常路，使用反射是可以破坏我们单例设计的
        Class<?> clazz = LazyInnerClassSingleton.class;
        Constructor c = clazz.getDeclaredConstructor(null);
        // 强吻
        c.setAccessible(true);
        // 通过反射创建的对象
        Object o1 = c.newInstance();
        // 通过单例设计模式获取到的对象
        LazyInnerClassSingleton o2 = LazyInnerClassSingleton.getInstance();
        System.out.println(o1 == o2);

//        LazyInnerClassSingleton o3 = LazyInnerClassSingleton.getInstance();
//        System.out.println(o2 == o3);
    }
}
