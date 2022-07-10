package com.raven.algorithm.exercise.二叉搜索树;

import java.util.LinkedList;

/**
 * Description:
 * date: 2022/7/10 21:15
 * https://leetcode.cn/problems/range-sum-of-bst/
 *
 * @author raven
 */
public class _938_二叉搜索树的范围和 {
    private LinkedList<TreeNode> list = new LinkedList<>();

    public int rangeSumBST(TreeNode root, int low, int high) {
        int sum = 0;
        if (root == null) {
            return sum;
        }
        inOrder(root);
        for (TreeNode node : list) {
            if (node.val >= low && node.val <= high) {
                sum = sum + node.val;
            }
        }
        return sum;
    }

    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        list.add(root);
        inOrder(root.right);
    }
}