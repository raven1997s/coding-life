package com.raven.algorithm.exercise.二叉搜索树;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Description:
 * date: 2022/7/10 16:43
 * https://leetcode.cn/problems/insert-into-a-binary-search-tree/
 *
 * @author raven
 */
public class _701_二叉搜索树中的插入操作 {
    public static TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val, null, null);
        }

        // 比较val 和树上元素的大小，确认要存储的位置
        TreeNode parent = root;
        // 用node进行迭代查询 ，找到要插入的位置
        TreeNode node = root;
        int cmp = 0;
        while (node != null){
            cmp = node.val - val;
            // 记录父节点
            parent = node;
            node = cmp > 0 ? node.left : node.right;
        }
        // 插入到parent的左边
        if (cmp > 0){
            parent.left = new TreeNode(val);
        }else {
            parent.right = new TreeNode(val);
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(4, new TreeNode(2, new TreeNode(1, null, null), new TreeNode(3, null, null)), new TreeNode(7, null, null));
        insertIntoBST(node,5);
    }
}