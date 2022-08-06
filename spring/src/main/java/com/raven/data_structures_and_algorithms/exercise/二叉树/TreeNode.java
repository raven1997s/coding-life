package com.raven.data_structures_and_algorithms.exercise.二叉树;

/**
 * Description:
 * date: 2022/6/26 20:22
 *
 * @author raven
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}