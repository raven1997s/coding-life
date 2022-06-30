package com.raven.algorithm.exercise.二叉树;

import java.util.LinkedList;
import java.util.List;

/**
 * Description:
 * date: 2022/6/30 22:17
 *
 * @author raven
 */
public class _590_N叉树的后序遍历 {
    public List<Integer> postorder(Node root) {
        List<Integer> result = new LinkedList<>();
        postorder(root, result);
        return result;
    }

    /**
     * 递归后续遍历N叉树
     * @param root
     * @param result
     */
    private void postorder(Node root, List<Integer> result) {
        if (root == null) {
            return;
        }

        for (Node node : root.children) {
            postorder(node, result);
        }
        result.add(root.val);
    }
}