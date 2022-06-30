package com.raven.algorithm.exercise.二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Description:
 * date: 2022/6/29 16:56
 * https://leetcode.cn/problems/binary-tree-level-order-traversal/
 *
 * @author raven
 */
public class _102_二叉树的层序遍历 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<Integer> temp = new LinkedList<>();
        // 记录每一层的元素个数
        int levelSize = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            levelSize--;
            temp.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
            }
            if (levelSize == 0) {
                levelSize = queue.size();
                result.add(temp);
                temp = new ArrayList<>();
            }
        }
        return result;
    }
}