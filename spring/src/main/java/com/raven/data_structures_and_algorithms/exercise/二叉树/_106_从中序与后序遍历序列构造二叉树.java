package com.raven.data_structures_and_algorithms.exercise.二叉树;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * date: 2022/7/3 18:35
 * https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 *
 * @author raven
 */
public class _106_从中序与后序遍历序列构造二叉树 {

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(postorder, map, 0, postorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] postorder, Map<Integer, Integer> map, int postLeft, int postRight, int inLeft, int inRight) {
        if (postLeft > postRight || inLeft > inRight) {
            return null;
        }
        int rooVal = postorder[postRight];
        TreeNode root = new TreeNode(rooVal);
        Integer inIndex = map.get(rooVal);

        root.left = buildTree(postorder, map, postLeft, postRight - inRight + inIndex - 1, inLeft, inIndex - 1);
        root.right = buildTree(postorder, map, postRight - inRight + inIndex, postRight - 1, inIndex + 1, inRight);
        return root;
    }
}