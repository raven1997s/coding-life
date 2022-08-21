package com.raven.data_structures_and_algorithms.structure.base_15_unionfind.entity;

/**
 * Description:
 * date: 2022/8/20 20:09
 *
 * @author raven
 */
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}