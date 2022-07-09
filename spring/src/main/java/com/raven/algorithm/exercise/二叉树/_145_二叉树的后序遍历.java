package com.raven.algorithm.exercise.二叉树;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * Description:
 * date: 2022/6/29 15:39
 * https://leetcode.cn/problems/binary-tree-postorder-traversal/
 *
 * @author raven
 */
public class _145_二叉树的后序遍历 {
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        postorderTraversal(root, result);
        return result;
    }

    /**
     * 递归方式
     *
     * @param node
     * @param result
     */
    private void postorderTraversal(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        postorderTraversal(node.left, result);
        postorderTraversal(node.right, result);
        result.add(node.val);
    }

    public List<Integer> postorderTraversal1(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        LinkedList<Integer> result = new LinkedList<>();
        if (null == root) {
            return result;
        }
        deque.addFirst(root);
        while (!deque.isEmpty()) {
            TreeNode node = deque.removeFirst();
            result.addFirst(node.val);
            if (null != node.left) {
                deque.addFirst(node.left);
            }
            if (null != node.right) {
                deque.addFirst(node.right);
            }
        }
        return result;
    }
}