package com.raven.data_structures_and_algorithms.exercise.二叉树;

import java.util.*;

/**
 * Description:
 * date: 2022/6/27 21:47
 * https://leetcode.cn/problems/binary-tree-inorder-traversal/
 *
 * @author raven
 */
public class _94_二叉树的中序遍历 {

    // 迭代方式
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            // 模拟递归遍历栈的调用 先将所有左节点入栈
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                // 当节点没有左节点时 证明到达树的最左边，弹出栈顶元素，转向右节点 ，继续上面的逻辑
                TreeNode top = stack.pop();
                result.add(top.val);
                root = top.right;
            }
        }
        return result;
    }


    /**
     * 中序遍历
     * 一路向左并把节点入栈，当节点没有左子树时弹出栈顶元素访问，访问后再访问右子树节点，然后继续前面的遍历。直至栈里元素为空
     */
    public List<Integer> inorder(TreeNode node) {
        List<Integer> result = new ArrayList<>();
        if (node == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        while (true) {
            if (node != null) {
                // 左子树进栈
                stack.push(node);
                node = node.left;
            } else if (stack.isEmpty()) {
                return result;
            } else {
                // 弹出栈顶元素访问
                node = stack.pop();
                result.add(node.val);
                // 让右子树进行中序遍历
                node = node.right;
            }
        }
    }

    /***
     * 中序遍历 递归方式
     * @param node
     * @param result
     */
    private void inorder(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        inorder(node.left, result);
        result.add(node.val);
        inorder(node.right, result);
    }
}