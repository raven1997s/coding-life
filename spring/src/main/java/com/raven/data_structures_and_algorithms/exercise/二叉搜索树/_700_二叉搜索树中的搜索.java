package com.raven.data_structures_and_algorithms.exercise.二叉搜索树;

/**
 * Description:
 * date: 2022/7/10 16:25
 * https://leetcode.cn/problems/search-in-a-binary-search-tree/
 *
 * @author raven
 */
public class _700_二叉搜索树中的搜索 {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return root;
        }

        while (root != null) {
            int cmr = root.val - val;
            if (cmr == 0) {
                return root;
            }
            root = cmr > 0 ? root.left : root.right;
        }
        return null;
    }
}