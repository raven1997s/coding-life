package com.raven.algorithm.structure.base_01_dynamicarray;

/**
 * Description:
 * date: 2022/5/5 22:32
 *
 * @author raven
 */
public class Assert {

    public static void test(boolean condition){
        if (!condition){
            throw new IllegalArgumentException("无效的参数!");
        }
    }
}