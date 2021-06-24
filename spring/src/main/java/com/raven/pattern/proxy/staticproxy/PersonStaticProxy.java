package com.raven.pattern.proxy.staticproxy;

import lombok.Data;

/**
 * @PackageName: com.raven.proxy.staticproxy
 * @ClassName: PersonStaticProxy
 * @Blame: raven
 * @Date: 2021-05-27 15:04
 * @Description: 静态代理类
 */
@Data
public class PersonStaticProxy implements Human {
    private Human human;

    public PersonStaticProxy() {
    }

    public PersonStaticProxy(Human human) {
        this.human = human;
    }

    @Override
    public void eat() {
        System.out.println("eat 前置加强！");
        // 代理类调用被代理的类方法
        human.eat();
        System.out.println("eat 后置加强！");
    }
}
