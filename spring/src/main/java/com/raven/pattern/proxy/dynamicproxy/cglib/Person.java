package com.raven.pattern.proxy.dynamicproxy.cglib;

import lombok.Data;

/**
 * @PackageName: com.raven.proxy.staticproxy
 * @ClassName: Person
 * @Blame: raven
 * @Date: 2021-05-27 14:59
 * @Description:
 */
@Data
public class Person  {
    private String name;
    private int age;

    public void eat() {
        System.out.println(name + ":eat food ...");
    }

    public void sleep() {
        System.out.println(name + ":sleep  ...");
    }
}
