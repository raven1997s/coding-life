package com.raven.data_structures_and_algorithms.exercise.栈;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @PackageName: com.raven.algorithm.exercise.栈
 * @ClassName: _20_有效的括号
 * @Blame: raven
 * @Date: 2022-06-16 21:22
 * @Description: https://leetcode.cn/problems/valid-parentheses/
 */
public class _20_有效的括号 {

    public static Map<Character, Character> map = new HashMap<>();

    static {
        map.put('{', '}');
        map.put('[', ']');
        map.put('(', ')');
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == '[' || c == '{' || c == '(') {
                stack.push(c);
            } else {
                // 插入右括号时，还没有插入过左括号。则一定无效
                if (stack.isEmpty()) {
                    return false;
                }
                Character left = stack.pop();
                if (c == ']' && left != '[') {
                    return false;
                }
                if (c == '}' && left != '{') {
                    return false;
                }
                if (c == ')' && left != '(') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("{}[()]"));
        System.out.println(isValid2("{}[()]"));
        System.out.println(isValid3("{}[()]"));
    }


    public static boolean isValid2(String s) {
        while (s.contains("[]") || s.contains("{}") || s.contains("()")) {
            s = s.replace("{}", "");
            s = s.replace("[]", "");
            s = s.replace("()", "");
        }
        return s.isEmpty();
    }

    public static boolean isValid3(String s) {
        Stack<Character> stack = new Stack<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                stack.push(c);
            } else {
                // 插入右括号时，还没有插入过左括号。则一定无效
                if (stack.isEmpty()) {
                    return false;
                }
                Character left = stack.pop();
                if (map.get(left) != c) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
