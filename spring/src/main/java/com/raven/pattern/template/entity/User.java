package com.raven.pattern.template.entity;

import lombok.Data;

/**
 * @PackageName: com.raven.pattern.template.entity
 * @ClassName: User
 * @Blame: raven
 * @Date: 2021-08-03 21:37
 * @Description:
 */
@Data
public class User {
    private String username;
    private String password;
    private int age;
    private String address;
}
