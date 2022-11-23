package com.raven.data_structures_and_algorithms.algorithms.sequence;

import com.raven.utils.Asserts;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

/**
 * Description:
 * date: 2022/11/22 16:58
 * 串匹配算法 - 蛮力算法
 *
 * @author raven
 */
public class BruteForce {

    public static void main(String[] args) {
        Asserts.test(indexOf("Hello World", "or") == 7);
        Asserts.test(indexOf("Hello World", "H") == 0);
        Asserts.test(indexOf("Hello World", "abc") == -1);
    }

    public static int indexOf2(String text, String pattern) {
        if (StringUtils.isEmpty(text) || StringUtils.isEmpty(pattern)) {
            return -1;
        }
        char[] textChars = text.toCharArray();
        char[] patternChars = pattern.toCharArray();
        int tlen = textChars.length;
        int plen = patternChars.length;
        if (tlen < plen) {
            return -1;
        }

        int pi = 0, ti = 0, lenDelta = tlen - plen;

        while (pi < plen && ti - pi < lenDelta) {
            if (textChars[ti] == patternChars[pi]) {
                ti++;
                pi++;
            } else {
                ti -= pi - 1;
                pi = 0;
            }
        }
        return (pi == plen) ? (ti - pi) : -1;
    }

    public static int indexOf(String text, String pattern) {
        if (StringUtils.isEmpty(text) || StringUtils.isEmpty(pattern)) {
            return -1;
        }
        char[] textChars = text.toCharArray();
        char[] patternChars = pattern.toCharArray();
        int tlen = textChars.length;
        int plen = patternChars.length;
        if (tlen < plen) {
            return -1;
        }

        int pi = 0, ti = 0;
        while (pi < plen && ti < tlen) {
            if (textChars[ti] == patternChars[pi]) {
                ti++;
                pi++;
            } else {
                ti -= pi - 1;
                pi = 0;
            }
        }
        return (pi == plen) ? (ti - pi) : -1;
    }
}