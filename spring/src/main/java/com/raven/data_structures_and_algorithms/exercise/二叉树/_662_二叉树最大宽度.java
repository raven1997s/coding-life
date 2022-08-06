package com.raven.data_structures_and_algorithms.exercise.二叉树;

import java.util.LinkedList;

/**
 * Description:
 * date: 2022/6/30 22:03
 * https://leetcode.cn/problems/maximum-width-of-binary-tree/
 * 解题思路
 * <p>
 * https://leetcode.cn/problems/maximum-width-of-binary-tree/solution/bfswan-quan-er-cha-shu-xing-zhi-kan-wan-qmguc/
 *
 * @author raven
 */
public class _662_二叉树最大宽度 {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 记录最大的宽度
        int maxWidth = 0;
        // 设置root的索引为0
        root.val = 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            //下一层个数
            int size = queue.size();
            // 宽度等于最后一个元素的位置 - 本层第一个元素的位置  + 1
            maxWidth = Math.max(maxWidth, queue.getLast().val - queue.getFirst().val + 1);

            // 添加下一层元素
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null){
                    node.left.val = node.val * 2;
                    queue.offer(node.left);
                }
                if (node.right != null){
                    node.right.val = node.val * 2 +1;
                    queue.offer(node.right);
                }
            }
        }
        return maxWidth;
    }
}