package com.raven.data_structures_and_algorithms.algorithms.sequence;

import com.raven.utils.Asserts;
import org.apache.commons.lang3.StringUtils;

/**
 * Description:
 * date: 2022/11/22 16:58
 * 串匹配算法 - 蛮力算法
 *
 * @author raven
 */
public class BruteForce2 {

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

        int tiMax = tlen - plen;
        for (int ti = 0; ti <= tiMax; ti++) {
            int pi = 0;
            for (; pi < plen; pi++) {
                if (textChars[ti + pi] != patternChars[pi]) {
                    break;
                }
            }
            if (pi == plen) {
                return ti;
            }
        }
        return -1;
    }
}