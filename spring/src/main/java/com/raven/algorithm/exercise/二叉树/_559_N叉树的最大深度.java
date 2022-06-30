package com.raven.algorithm.exercise.二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Description:
 * date: 2022/6/30 22:24
 * https://leetcode.cn/problems/maximum-depth-of-n-ary-tree/
 *
 * @author raven
 */
public class _559_N叉树的最大深度 {

    /**
     * 层序遍历求高度
     * @param root
     * @return
     */
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int levelSize = 1;
        int height = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            levelSize--;
            if (node.children.size() != 0) {
                for (Node childNode : node.children) {
                    queue.offer(childNode);
                }
            }
            if (levelSize == 0) {
                height++;
                levelSize = queue.size();
            }
        }
        return height;
    }


    /**
     * 递归方式求高度
     * @param root
     * @return
     */
    public int maxDepth1(Node root) {
        if (root == null) {
            return 0;
        }

        int height = 0;
        for (Node node : root.children) {
            // 获取子节点中最深的深度
            height = Math.max(maxDepth1(node), height);
        }
        // 高度等于下一层的高度+1
        return height + 1;
    }
}