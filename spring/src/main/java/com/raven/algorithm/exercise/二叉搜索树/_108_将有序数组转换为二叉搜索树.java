package com.raven.algorithm.exercise.二叉搜索树;

/**
 * Description:
 * date: 2022/7/10 18:23
 * https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/
 *
 * @author raven
 */
public class _108_将有序数组转换为二叉搜索树 {
    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    private TreeNode dfs(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        // 找到中间元素
        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        // 递归构建子树
        node.left = dfs(nums, start, mid - 1);
        node.right = dfs(nums, mid + 1, end);
        return node;
    }
}