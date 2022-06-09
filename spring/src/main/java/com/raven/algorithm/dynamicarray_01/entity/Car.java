package com.raven.algorithm.dynamicarray_01.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Description:
 * date: 2022/5/6 20:17
 *
 * @author raven
 */
@Data
@AllArgsConstructor
public class Car {
    private String name;
    private int age;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Car - Over");
    }


}