package com.raven.algorithm.exercise.栈;

import org.apache.commons.lang3.StringUtils;

import java.util.Stack;

/**
 * @PackageName: com.raven.algorithm.exercise.栈
 * @ClassName: _150_逆波兰表达式求值
 * @Blame: raven
 * @Date: 2022-06-16 22:18
 * @Description: https://leetcode.cn/problems/evaluate-reverse-polish-notation/
 */
public class _150_逆波兰表达式求值 {

    public static void main(String[] args) {
        //3 4 + 5 × 6 -
        //int result = evalRPN(new String[]{"3", "4", "+", "5", "*", "6", "-"});
        //String[] tokens = new String[]{"2","1","+","3","*"};
        //String[] tokens = new String[]{"4","13","5","/","+"};
        String[] tokens = new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        System.out.println(evalRPN(tokens));
    }

    public static int evalRPN(String[] tokens) {
        // 存储数字
        Stack<Integer> numberStack = new Stack<>();
        // 从左到右扫描
        for (String token : tokens) {
            if (isOp(token)) {
                // 如果是操作符。取出栈顶元素和次栈顶元素 计算后放入栈中
                Integer top = numberStack.pop();
                Integer second = numberStack.pop();
                numberStack.push(calc(second, top, token));
            } else {
                // 如果是数字 直接放入栈中
                numberStack.push(Integer.valueOf(token));
            }
        }
        return numberStack.pop();
    }

    /**
     * 判断字符串是不是数字
     *
     * @param str
     * @return
     */
    private static boolean isNumber(String str) {
        return str != null && str.chars().allMatch(Character::isDigit);
    }

    private static boolean isOp(String str) {
        return "+".equals(str) || "-".equals(str) || "*".equals(str) || "/".equals(str);
    }

    /**
     * 计算
     *
     * @param num1
     * @param num2
     * @param op
     * @return
     */
    private static int calc(Integer num1, Integer num2, String op) {
        try {
            switch (op) {
                case "+":
                    return num1 + num2;
                case "-":
                    return num1 - num2;
                case "*":
                    return num1 * num2;
                case "/":
                    return num1 / num2;
                default:
                    return 0;
            }
        } catch (IllegalArgumentException e) {
            return 0;
        }
    }


}
