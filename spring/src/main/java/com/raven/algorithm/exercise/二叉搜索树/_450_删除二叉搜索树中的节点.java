package com.raven.algorithm.exercise.二叉搜索树;

/**
 * Description:
 * date: 2022/7/10 14:51
 * https://leetcode.cn/problems/delete-node-in-a-bst/
 *  当时通过官方题解学习的 面试前着重学习一下
 * @author raven
 */
public class _450_删除二叉搜索树中的节点 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        // 要删除的数值小于当前节点的值
        if (root.val > key) {
            // 去左子树中删除元素
            root.left = deleteNode(root.left, key);
            return root;
        } else if (root.val < key) {
            // 去右子树中删除元素
            root.right = deleteNode(root.right, key);
            return root;
        } else {
            // 节点度为0
            // 当前节点就是需要删除的节点
            if (root.left == null && root.right == null) {
                //叶子节点直接删除
                return null;
            }

            // 节点度为1
            // 节点右子树为空
            if (root.right == null) {
                return root.left;
            }
            // 节点左子树为空
            if (root.left == null) {
                return root.right;
            }

            // 节点度为2
            // 找到后继节点
            TreeNode successor = root.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            // 将后继节点删除 用后继节点替换原来要被删除的节点
            root.right = deleteNode(root.right, successor.val);
            successor.right = root.right;
            successor.left = root.left;
            return successor;
        }
    }
}