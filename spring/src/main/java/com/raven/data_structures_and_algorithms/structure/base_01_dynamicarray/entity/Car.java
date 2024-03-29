package com.raven.data_structures_and_algorithms.structure.base_01_dynamicarray.entity;

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