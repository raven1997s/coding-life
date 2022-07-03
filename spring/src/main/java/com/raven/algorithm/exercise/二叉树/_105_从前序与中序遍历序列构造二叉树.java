package com.raven.algorithm.exercise.二叉树;

import jdk.internal.util.xml.impl.Pair;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * Description:
 * date: 2022/7/3 12:00
 * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 *
 * @author raven
 */
public class _105_从前序与中序遍历序列构造二叉树 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        int inLen = inorder.length;
        int preLen = preorder.length;
        // 记录中序遍历中每个元素的index位置
        Map<Integer, Integer> inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inLen; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        // 通过递归构建二叉树
        return buildTree(preorder, 0, preLen - 1, inorderIndexMap, 0, inLen - 1);
    }

    /**
     * @param preorder        前序遍历的元素列表
     * @param preLeft         前序遍历元素的左边界
     * @param preRight        前序遍历元素的右边界
     * @param inorderIndexMap 元素再中序遍历中的位置
     * @param inLeft          中序遍历元素的左边界
     * @param inRight         中序遍历元素的左边界
     * @return 返回构建的节点
     */
    private TreeNode buildTree(int[] preorder, int preLeft, int preRight, Map<Integer, Integer> inorderIndexMap, int inLeft, int inRight) {
        // 区间不构成区间时终止递归
        if (preLeft > preRight || inLeft > inRight || !inorderIndexMap.containsKey(preorder[preLeft])) {
            return null;
        }
        int rootVal = preorder[preLeft];
        TreeNode node = new TreeNode(rootVal);
        // 获取元素在中序遍历中的位置
        int inIndex = inorderIndexMap.get(rootVal);
        // 前序遍历 【 根 左子树  右子树 】
        // 中序遍历 【左子树 根   右子树 】
        // 定义: 根在中序遍历的index为inIndex 左子树的左边界为inLeft 右子树的右边界为inRight
        // 中序遍历左子树右边界则为 inIndex - 1
        // 中序遍历右子树左边界为 inIndex+1
        // 定义：前序遍历 根为preLeft  左子树左边界为 preLeft + 1  右子树右边界为preRight
        // 因为左子树的个数无论前序遍历还是中序遍历个数是相同的
        // inIndex -1 - inLeft (中序遍历左子树元素个数) = x（前序遍历左子树右边界） - （preLeft + 1）
        // 则前序遍历左子树右边界为  inIndex - inLeft + preLeft
        // 前序遍历右子树左边界为   inIndex - inLeft + preLeft +1
        node.left = buildTree(preorder, preLeft + 1, inIndex - inLeft + preLeft, inorderIndexMap, inLeft, inIndex - 1);
        node.right = buildTree(preorder, inIndex - inLeft + preLeft + 1, preRight, inorderIndexMap, inIndex + 1, inRight);
        return node;
    }


}