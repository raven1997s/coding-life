package com.raven.algorithm.exercise.二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Description:
 * date: 2022/6/30 21:18
 * https://leetcode.cn/problems/maximum-depth-of-binary-tree/
 *
 * @author raven
 */
public class _104_二叉树的最大深度 {

    /**
     * 层序遍历 迭代方式
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int height = 0;
        // 本层剩余元素数量
        int levelSize = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            levelSize--;
            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }

            if (levelSize == 0) {
                height++;
                levelSize = queue.size();
            }
        }
        return height;
    }

    public int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth1(root.right), maxDepth1(root.left));
    }
}