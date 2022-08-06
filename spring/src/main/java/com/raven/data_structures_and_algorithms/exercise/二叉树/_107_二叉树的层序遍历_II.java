package com.raven.data_structures_and_algorithms.exercise.二叉树;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Description:
 * date: 2022/6/30 21:36
 * https://leetcode.cn/problems/binary-tree-level-order-traversal-ii/
 *
 * @author raven
 */
public class _107_二叉树的层序遍历_II {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }

        int levelSize = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> tempList = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            levelSize--;
            tempList.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }

            if (levelSize == 0) {
                levelSize = queue.size();
                result.addFirst(tempList);
                tempList = new LinkedList<>();
            }
        }
        return result;
    }

}
