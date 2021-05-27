package com.raven.proxy.staticproxy;

/**
 * @PackageName: com.raven.proxy.main
 * @ClassName: StaticProxyMain
 * @Blame: raven
 * @Date: 2021-05-27 15:02
 * @Description: 静态代理
 * 1.需要显式的创建代理类对象
 * 2.和被代理类实现同一接口
 * 3.在代理类中对方法进行加强
 */
public class StaticProxyMain {
    public static void main(String[] args) {
        // 原始类调用方法
        Person person = new Person();
        person.setName("张三");
        person.eat();

        System.out.println("===============");
        // 静态代理类调用方法
        Human proxy = new PersonStaticProxy(person);
        proxy.eat();
    }
}
