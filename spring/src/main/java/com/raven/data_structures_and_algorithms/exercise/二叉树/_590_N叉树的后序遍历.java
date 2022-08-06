package com.raven.data_structures_and_algorithms.exercise.二叉树;

import java.util.*;

/**
 * Description:
 * date: 2022/6/30 22:17
 *
 * @author raven
 */
public class _590_N叉树的后序遍历 {
    public List<Integer> postorder(Node root) {
        LinkedList<Integer> result = new LinkedList<>();
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