package com.raven.data_structures_and_algorithms.exercise.栈;

import java.util.Stack;

/**
 * @PackageName: com.raven.algorithm.exercise.栈
 * @ClassName: _856_括号的分数
 * @Blame: raven
 * @Date: 2022-06-16 22:16
 * @Description: https://leetcode.cn/problems/score-of-parentheses/
 */
public class _856_括号的分数 {

    public static void main(String[] args) {
        //输入： "()"
        //输出： 1
        //示例 2：
        //
        //输入： "(())"
        //输出： 2
        //示例  3：
        //
        //输入： "()()"
        //输出： 2
        //示例 4：
        //
        //输入： "(3 )"
        //输出： 6

        //System.out.println("A1===" + scoreOfParentheses("()"));
        //System.out.println("A2===" + scoreOfParentheses("(())"));
        //System.out.println("A3===" + scoreOfParentheses("()()"));
        //System.out.println("A4===" + scoreOfParentheses("(()(()))"));
        System.out.println("A5===" + scoreOfParentheses("(()(()()))"));
        // "(()(()()))"
    }

    public static int scoreOfParentheses(String s) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            String c = String.valueOf(s.charAt(i));
            if ("(".equals(c)) {
                stack.push(c);
            } else {
                // 当前字符为) 括号
                String top = stack.pop();
                // 栈顶为左括号 则弹出左括号 根据() = 1 将1入栈
                if ("(".equals(top)) {
                    stack.push("1");
                } else {
                    int temp = 0;
                    // 栈顶为数字 弹出次栈顶 进行计算
                    while (!"(".equals(top)) {
                        temp = Integer.parseInt(top) + temp;
                        top = stack.pop();
                    }
                    // 最后一个  根据( n) = 2* n 计算后将 n入栈
                    stack.push(String.valueOf(temp * 2));
                }
            }
        }
        int sum = 0;
        while (!stack.isEmpty()) {
            sum = sum + Integer.parseInt(stack.pop());
        }
        return sum;
    }

}
