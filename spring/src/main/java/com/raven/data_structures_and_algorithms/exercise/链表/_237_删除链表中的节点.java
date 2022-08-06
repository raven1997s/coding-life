package com.raven.data_structures_and_algorithms.exercise.链表;

/**
 * Description:
 * date: 2022/6/9 22:18
 * https://leetcode.cn/problems/delete-node-in-a-linked-list/
 *
 * @author raven
 */
public class _237_删除链表中的节点 {

    public void deleteNode(ListNode node) {
        // 要删除指定的node，无法获取当前节点的前一个节点
        // 把后一个节点的值赋值给当前节点
        // 把当前节点的next指向后一个节点的next 删除后一个节点 从而达到删除当前节点的目的
        // （//既然不能先删除自己，那就把自己整容成儿子，再假装儿子养活孙子）
        node.val = node.next.val;
        node.next = node.next.next;
    }
}