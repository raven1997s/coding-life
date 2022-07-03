package com.raven.algorithm.exercise.二叉树;

/**
 * Description:
 * date: 2022/7/3 11:16
 * https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 *
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *
 * @author raven
 */
public class _114_二叉树展开为链表 {

    public void flatten(TreeNode root) {



    }
    // TODO(raven): 2022/7/3 ????不会
    private void preorder(TreeNode node){
        if (node == null){
            return;
        }

        int cur = node.val;

        preorder(node.left);
        preorder(node.right);

    }

}