package com.raven.algorithm.exercise.二叉树;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

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

    /**
     * 迭代方式
     *
     * @param root
     * @return
     */
    private void postorderTraversal1(TreeNode root, LinkedList<Integer> result) {
        if (root == null) {
            return;
        }
        // TODO(raven): 2022/6/29 待理解
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                result.addFirst(root.val);
                root = root.right;
            } else {
                root = stack.pop();
                root = root.left;
            }
        }
    }
}