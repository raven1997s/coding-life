package com.raven.algorithm.exercise.队列;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Description:
 * date: 2022/6/19 08:55
 *
 * @author raven
 */
public class _225_用队列实现栈 {
    private Deque<Integer> deque = new LinkedList<>();

    public _225_用队列实现栈() {

    }

    public void push(int x) {
        deque.add(x);
    }

    public int pop() {
        if (deque.isEmpty()) {
            return 0;
        }
        return deque.removeLast();
    }

    public int top() {
        if (deque.isEmpty()) {
            return 0;
        }
        return deque.getLast();
    }

    public boolean empty() {
        return deque.isEmpty();
    }
}