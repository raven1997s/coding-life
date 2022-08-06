package com.raven.data_structures_and_algorithms.exercise.栈;

import java.util.Stack;

/**
 * @PackageName: com.raven.algorithm.exercise.栈
 * @ClassName: _224_基本计算器
 * @Blame: raven
 * @Date: 2022-06-16 22:19
 * @Description: https://leetcode.cn/problems/basic-calculator/
 */
public class _224_基本计算器 {

    // 参考文档 https://blog.csdn.net/Antineutrino/article/details/6763722
    // 思路：
    // 1。将中缀表达式转为后缀表达式 (逆波兰式) 2。利用150 逆波兰式求值 (笨办法)
    // 2。利用正负号将括号击穿 直接计算(奇技淫巧)

    public static void main(String[] args) {
        System.out.println(calculate1("(1+(4+5+2)-3)+(6+8)"));
    }
    public int calculate(String s) {
        return 0;
    }


    /**
     * 有问题 ****
     * @param s
     * @return
     */
    private static int calculate1(String s) {
        Stack<Character> st = new Stack<>();
        int ans = 0;
        int num = 0;
        char op = '1';
        st.push(op);

        for (char c : s.toCharArray()) {
            if (c == ' ') {
                continue;
            } else if (c >= '0') {
                num = num * 10 - '0' + c;
            } else {
                ans = ans + op * num;
                num = 0;

                if (c == '+') {
                    op = st.pop();
                } else if (c == '-') {
                    op = (char) -st.pop();
                } else if (c == '(') {
                    st.push(op);
                } else {
                    st.pop();
                }
            }
        }
        return ans + op * num;
    }


    /**
     * 将中缀表达式转为后缀表达式 (逆波兰式)
     *
     * @param s
     * @return
     */
    private String convertor(String s) {
        Stack<Character> numberStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();
        for (char c : s.toCharArray()) {
            // 判断是否为空字符 空字符跳过
            if (Character.isSpaceChar(c)) {
                continue;
            }
            // 判断是否式运算符
            if (isOp(c)) {
                // 如果操作符栈为空 或者操作符栈栈顶运算符为（ 则运算符直接入栈
                if (opStack.isEmpty() || '(' == opStack.peek()) {
                    opStack.push(c);
                } else {
                    // 否则 比较运算符优先级 如果优先级高于栈顶运算符 则运算符直接入栈
                    if (priorityCompare(c, opStack.peek()) > 0) {
                        opStack.push(c);
                    } else {
                        // 否则 将栈顶的运算符弹出 放入数字操作数栈中
                        numberStack.push(opStack.pop());
                        // TODO(raven): 2022/6/18 有点点难，他日再战！！
                    }
                }
            } else {
                // 数字直接放入操作数栈
                numberStack.push(c);
            }
        }
        return "";
    }

    private static boolean isOp(char c) {
        return ('+' == c || '-' == c || '*' == c || '/' == c);
    }

    private static int priorityCompare(char op1, char op2) {
        switch (op1) {
            case '+':
            case '-':
                return ('*' == op2 || '/' == op2 ? -1 : 0);
            case '*':
            case '/':
                return ('+' == op2 || '-' == op2 ? 1 : 0);
            default:
                return 1;
        }
    }
}
