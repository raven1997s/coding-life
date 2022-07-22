package com.raven.pattern.proxy.staticproxy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PackageName: com.raven.proxy.staticproxy
 * @ClassName: Person
 * @Blame: raven
 * @Date: 2021-05-27 14:59
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Human {
    private String name;
    private int age;

    @Override
    public void eat() {
        System.out.println(name + ":eat food ...");
    }
}
