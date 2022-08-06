package com.raven.data_structures_and_algorithms.exercise.二叉树;


import java.util.LinkedList;
import java.util.Queue;

/**
 * Description:
 * date: 2022/6/26 19:56
 * https://leetcode.cn/problems/invert-binary-tree/
 *
 * @author raven
 */
public class _226_翻转二叉树 {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        // 前序遍历 递归方式
        // 翻转二叉树交换每一个节点的左右节点即可
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return root;
        }
        // 中序遍历 递归方式
        // 翻转二叉树交换每一个节点的左右节点即可
        invertTree(root.left);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        // 需要注意 此时left和right已经交换顺序 所以下面传入left 就是传入 right
        invertTree(root.left);
        return root;
    }


    public TreeNode invertTree3(TreeNode root) {
        if (root == null) {
            return root;
        }
        // 后序遍历 递归方式
        // 翻转二叉树交换每一个节点的左右节点即可
        invertTree(root.left);
        invertTree(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }

    public TreeNode invertTree4(TreeNode root) {
        if (root == null) {
            return root;
        }
        // 层序遍历 递归方式
        // 翻转二叉树交换每一个节点的左右节点即可
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            // 交换node的left和right
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return root;
    }
}