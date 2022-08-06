package com.raven.data_structures_and_algorithms.exercise.二叉搜索树;

import com.raven.data_structures_and_algorithms.structure.base_04_queue.list.LinkedList;

/**
 * Description:
 * date: 2022/7/10 18:13
 * https://leetcode.cn/problems/minimum-distance-between-bst-nodes/
 *
 * @author raven
 */
public class _783_二叉搜索树节点最小距离 {
    private LinkedList<TreeNode> list = new LinkedList<>();

    public int minDiffInBST(TreeNode root) {
        inOrder(root);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list.size() - 1; i++) {
            min = Math.min(min, list.get(i + 1).val - list.get(i).val);
        }
        return min;
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