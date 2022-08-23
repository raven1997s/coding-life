package com.raven.selenium.ocr.util;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Description:
 * date: 2022/8/18 14:16
 *
 * @author raven
 */
public class CalculateUtil {
    public static int calculate(String words) {
        int num1 = Integer.parseInt(words.charAt(0) + "");
        int num2 = Integer.parseInt(words.charAt(2) + "");
        char opChar = words.charAt(1);
        int res;
        switch (opChar) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num1 - num2;
                break;
            case '*':
                res = num1 * num2;
                break;
            default:
                res = num1 * num2;
        }
        return res;
    }
}