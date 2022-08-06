package com.raven.data_structures_and_algorithms.structure.utils;

/**
 * Description:
 * date: 2022/5/5 22:32
 *
 * @author raven
 */
public class Asserts {

    public static void test(boolean condition){
        if (!condition){
            throw new IllegalArgumentException("无效的参数!");
        }
    }
}