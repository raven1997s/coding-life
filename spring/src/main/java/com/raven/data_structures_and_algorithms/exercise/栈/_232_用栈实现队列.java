package com.raven.data_structures_and_algorithms.exercise.栈;

import java.util.Stack;

/**
 * Description:
 * date: 2022/6/18 19:04
 * https://leetcode.cn/problems/implement-queue-using-stacks/
 *
 * @author raven
 */
public class _232_用栈实现队列 {

    private Stack<Integer> inStack = new Stack<>();
    private Stack<Integer> outStack = new Stack<>();

    public _232_用栈实现队列() {

    }

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        in2out();
        return outStack.pop();
    }

    public int peek() {
        in2out();
        return outStack.peek();
    }

    public boolean empty() {
        return outStack.isEmpty() && inStack.isEmpty();
    }


    private void in2out() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }
}