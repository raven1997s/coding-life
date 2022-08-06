package com.raven.algorithm.exercise.二叉树;

import com.raven.algorithm.structure.base_14_tree_traversal.BinaryTree;

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

    /**
     * 前序遍历
     * 先遍历节点 并把右子树放入栈中 然后一路向左遍历  当没有左子树时弹出栈顶 继续遍历 直到栈中元素全部遍历完毕
     *
     */
    public void preorder2(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (true) {
            if (node != null) {
                // 访问节点
                result.add(node.val);
                // 右子树进栈
                if (node.right != null) {
                    stack.push(node.right);
                }
                // 向左走
                node = node.left;
            } else if (stack.isEmpty()) {
                // 栈为null 遍历完毕
                return;
            } else {
                // 节点没有左子树时访问栈顶元素
                node = stack.pop();
            }
        }

    }

}