package com.raven.algorithm.exercise.二叉搜索树;

/**
 * Description:
 * date: 2022/7/10 21:48
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/
 *
 * @author raven
 */
public class _235_二叉搜索树的最近公共祖先 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }


    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        while (true) {
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            }else if (p.val > root.val && q.val > root.val) {
                root = root.right;
            }else {
                return root;
            }
        }
    }


}