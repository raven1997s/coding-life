package com.raven.algorithm.exercise.二叉搜索树;

import java.util.LinkedList;

/**
 * Description:
 * date: 2022/7/10 22:12
 * https://leetcode.cn/problems/kth-smallest-element-in-a-bst/
 *
 * @author raven
 */
public class _230_二叉搜索树中第K小的元素 {
    private LinkedList<TreeNode> list = new LinkedList<>();

    public int kthSmallest(TreeNode root, int k) {
        int result = 0;
        if (root == null) {
            return result;
        }
        inOrder(root);
        //
        //for (int i = 1; i <= list.size(); i++) {
        //    if (i == k) {
        //        result = list.get(i - 1).val;
        //        break;
        //    }
        //}
        //return result;
        return list.get(k - 1).val;
    }


    private void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        list.add(node);
        inOrder(node.right);
    }
}