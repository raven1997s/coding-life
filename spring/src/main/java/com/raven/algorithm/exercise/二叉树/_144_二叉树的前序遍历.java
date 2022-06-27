package com.raven.algorithm.exercise.二叉树;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Description:
 * date: 2022/6/27 20:50
 * https://leetcode.cn/problems/binary-tree-preorder-traversal/
 *
 * @author raven
 */
public class _144_二叉树的前序遍历 {
    private final List<Integer> result = new LinkedList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        preorder(root);
        return result;
    }

    /**
     * 递归方式
     *
     * @param node
     */
    private void preorder(TreeNode node) {
        if (node == null) {
            return;
        }
        result.add(node.val);
        preorder(node.left);
        preorder(node.right);
    }

    private void preorder(TreeNode node, List<Integer> result) {
        if (node == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            // 取出栈顶元素
            TreeNode top = stack.pop();
            // 存入结果集中
            result.add(top.val);
            // 依旧栈先进后出原则，将右节点先入栈 左节点后入栈
            // 取出时先取出左节点，存储结果集 后取出右节点 (当左节点 有子节点时，先取出左节点(此时他相当于父节点) 会将左节点当右子节点先入栈 ，左子节点后入栈)
            // 按上面当规则，则会永远 父节点 -》右子节点 -》左子节点 当方式入栈 会以父节点-》左子节点 -》右子节点的方式存储元素
            if (top.right != null){
                stack.add(top.right);
            }

            if (top.left != null){
                stack.add(top.left);
            }
        }
    }

}