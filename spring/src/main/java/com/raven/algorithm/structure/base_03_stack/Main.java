package com.raven.algorithm.structure.base_03_stack;

/**
 * @PackageName: com.raven.algorithm.structure.base_03_stack
 * @ClassName: Main
 * @Blame: raven
 * @Date: 2022-06-15 23:01
 * @Description:
 */
public class Main {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        System.out.println(stack);
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
}
