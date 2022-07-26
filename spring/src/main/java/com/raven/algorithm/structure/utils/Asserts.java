package com.raven.algorithm.structure.utils;

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