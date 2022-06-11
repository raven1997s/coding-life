package com.raven.algorithm.exercise.链表;

/**
 * Description:
 * date: 2022/6/11 12:20
 * https://leetcode.cn/problems/middle-of-the-linked-list/
 *
 * @author raven
 */
public class _876_链表的中间结点 {
    /**
     * 快慢指针处理
     *
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 快慢指针处理
        ListNode fast = head;
        ListNode slow = head;
        // 当快指针只走完后，慢指针此时处于最中间当位置
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 利用数组下标 索引获取元素
     *
     * @param head
     * @return
     */
    public ListNode middleNode2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 遍历将链表放入数组中，通过索引当方式获取最中间index的链表节点
        ListNode[] array = new ListNode[100];
        int index = 0;
        while (head != null) {
            array[index] = head;
            head = head.next;
            index++;
        }
        return array[index / 2];
    }
}