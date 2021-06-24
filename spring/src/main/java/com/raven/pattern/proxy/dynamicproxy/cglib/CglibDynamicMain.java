package com.raven.pattern.proxy.dynamicproxy.cglib;


/**
 * @PackageName: com.raven.proxy.dynamicproxy.jdk
 * @ClassName: JdkDynamicMain
 * @Blame: raven
 * @Date: 2021-05-27 15:31
 * @Description: cglib 动态代理
 * <p>
 * 1.cglib动态代理 中代理工厂需要实现MethodInterceptor接口
 * 2.在重写的intercept方法中对类进行加强
 * 3.使用Enhancer指定回调函数以及动态代理类的父类
 */
public class CglibDynamicMain {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("张三");
        Person personProxy = (Person) new CglibFactory(person).createProxy();
        personProxy.eat();
    }
}
