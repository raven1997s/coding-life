package com.raven.algorithm.basestudy;

import org.junit.Test;

import java.util.Arrays;

/**
 * Description:
 * date: 2022/1/28 11:40
 *
 * @author raven
 */
public class BaseAlgorithmTest {

    @Test
    public void Test1() {
        //在一个字符串中寻找没有重复字母的最长子串
        String s = "aaqw112abbc";
        String str = getMaxStr(s);
        System.out.println(str);
    }

    private String getMaxStr(String s) {
        StringBuilder str = new StringBuilder();
            for (char c : s.toCharArray()) {
                if (!str.toString().contains(String.valueOf(c))) {
                    str.append(c);
                }
            }
        return str.toString();
    }

}