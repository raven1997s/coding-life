package com.raven.data_structures_and_algorithms.exercise.二叉树;

import java.util.List;

/**
 * Description:
 * date: 2022/6/30 22:01
 *
 * @author raven
 */
public class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}