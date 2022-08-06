package com.raven.data_structures_and_algorithms.exercise.二叉树;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Description:
 * date: 2022/6/30 22:07
 * https://leetcode.cn/problems/n-ary-tree-preorder-traversal/
 *
 * @author raven
 */
public class _589_N叉树的前序遍历 {

    public List<Integer> preorder(Node root) {
        List<Integer> result = new LinkedList<>();
        preorder(root, result);
        return result;
    }

    /**
     * 递归前序遍历n叉树
     * @param root
     * @param result
     */
    private void preorder(Node root, List<Integer> result) {
        if (root == null) {
            return;
        }

        result.add(root.val);
        for (Node node : root.children) {
            preorder(node, result);
        }
    }

    private void preorder1(Node root, List<Integer> result) {
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node node = stack.pop();
            result.add(node.val);
            int size = node.children.size();
            for (int i = size -1 ; i >= 0; i--) {
                stack.push(node.children.get(i));
            }
        }

    }
}