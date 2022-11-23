package com.raven.data_structures_and_algorithms.algorithms.sequence;

import com.raven.utils.Asserts;
import org.apache.commons.lang3.StringUtils;

/**
 * Description:
 * date: 2022/11/23 16:56
 *
 * @author raven
 */
public class KMP {
    public static void main(String[] args) {
        Asserts.test(indexOf("Hello World", "or") == 7);
        Asserts.test(indexOf("Hello World", "H") == 0);
        Asserts.test(indexOf("Hello World", "abc") == -1);
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

        int[] next = next(pattern);

        int pi = 0, ti = 0, lenDelta = tlen - plen;
        while (pi < plen && ti - pi < lenDelta) {
            if (pi < 0 || textChars[ti] == patternChars[pi]) {
                ti++;
                pi++;
            } else {
                pi = next[pi];
            }
        }
        return (pi == plen) ? (ti - pi) : -1;
    }

    private static int[] next(String pattern) {
        char[] chars = pattern.toCharArray();
        int[] next = new int[chars.length];
        next[0] = -1;
        int i = 0;
        int iMax = chars.length - 1;
        int n = -1;
        while (i < iMax) {
            if (n < 0 || chars[i] == chars[n]) {
                next[++i] = ++n;
            } else {
                n = next[n];
            }
        }
        return next;
    }

    private static int[] next2(String pattern) {
        char[] chars = pattern.toCharArray();
        int[] next = new int[chars.length];
        next[0] = -1;
        int i = 0;
        int iMax = chars.length - 1;
        int n = -1;
        while (i < iMax) {
            if (n < 0 || chars[i] == chars[n]) {
                ++i;
                ++n;
                if (chars[i] == chars[n]){
                    next[i] = next[n];
                }else {
                    next[i] = n;
                }
            } else {
                n = next[n];
            }
        }
        return next;
    }
}