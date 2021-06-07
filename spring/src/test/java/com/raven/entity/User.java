package com.raven.entity;

import lombok.Data;

/**
 * @PackageName: com.raven.stream.entity
 * @ClassName: User
 * @Blame: raven
 * @Date: 2021-06-02 15:53
 * @Description:
 */
@Data
public class User {
    private String name;
    private int age;
    private String address;

    public User(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
}
