package com.raven.pattern.proxy.dynamicproxy.jdk;

import lombok.Data;

/**
 * @PackageName: com.raven.proxy.staticproxy
 * @ClassName: Person
 * @Blame: raven
 * @Date: 2021-05-27 14:59
 * @Description:
 */
@Data
public class Person implements Human {
    private String name;
    private int age;

    @Override
    public void eat() {
        System.out.println(name + ":eat food ...");
    }

    @Override
    public void sleep() {
        System.out.println(name + ":sleep  ...");
    }
}
