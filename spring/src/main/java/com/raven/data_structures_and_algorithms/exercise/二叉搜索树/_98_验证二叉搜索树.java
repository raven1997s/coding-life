package com.raven.data_structures_and_algorithms.exercise.二叉搜索树;

import java.util.LinkedList;

/**
 * Description:
 * date: 2022/7/10 17:47
 * https://leetcode.cn/problems/validate-binary-search-tree/
 *
 * @author raven
 */
public class _98_验证二叉搜索树 {
    private LinkedList<TreeNode> list = new LinkedList<>();

    /**
     * 利用中序遍历将元素排序，排序后如果前一个元素大于等于后一个元素，则不是二叉搜索树
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        inOrder(root);
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).val >= list.get(i + 1).val) {
                return false;
            }
        }
        return true;
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