package com.raven.utils;

/**
 * Description:
 * date: 2022/5/5 22:32
 *
 * @author raven
 */
public class Asserts {

    public static void test(boolean condition) {
        try {
            if (!condition) {
                throw new Exception("测试未通过");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}