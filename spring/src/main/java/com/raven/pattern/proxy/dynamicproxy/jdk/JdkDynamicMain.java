package com.raven.pattern.proxy.dynamicproxy.jdk;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Proxy;

/**
 * @PackageName: com.raven.proxy.dynamicproxy.jdk
 * @ClassName: JdkDynamicMain
 * @Blame: raven
 * @Date: 2021-05-27 15:31
 * @Description: jdk 动态代理
 * <p>
 * 1.jdk动态代理为隐式的创建代理对象
 * 2.代理类会实现被代理类所实现的所有接口
 */
public class JdkDynamicMain {
    public static void main(String[] args) {
        final Person person = new Person();
        person.setName("李四");

        /**
         * 第一个参数: 被代理类的类加载器
         * 第二个参数: 被代理类所实现的所有接口
         * 第三个参数: 执行处理逻辑
         */
        Human human = (Human) Proxy.newProxyInstance(Person.class.getClassLoader(), Person.class.getInterfaces(),
                (proxy, method, args1) -> {
                    // 只有方法为 eat时 才进行加强
                    if (StringUtils.equals(method.getName(), "eat")) {
                        System.out.println("动态代理前置加强...");
                        Object invoke = method.invoke(person, args1);
                        System.out.println("动态代理后置加强...");
                        return invoke;
                    } else {
                        return method.invoke(person, args1);
                    }
                });

        /**
         * 执行代理方法
         */
        human.eat();
        System.out.println("==========");
        /**
         * 执行其他方法
         */
        human.sleep();

    }
}
