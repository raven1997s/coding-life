package com.raven.algorithm.exercise.链表;

import java.util.List;

/**
 * Description:
 * date: 2022/6/9 22:37
 * https://leetcode.cn/problems/reverse-linked-list/
 *
 * @author raven
 */
public class _206_反转链表 {

    public ListNode reverseList(ListNode head) {
        // 递归终止条件
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        // 当前节点的下一个节点 指向当前节点，
        head.next.next = head;
        // 当前节点指向空，完成反转
        head.next = null;
        return newHead;
    }

    public ListNode reverseList2(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            // 获取下一个节点保存起来
            ListNode next = cur.next;
            // 把当前节点的下一个指向 前一个节点 达到反转的作用
            cur.next = pre;
            // 把前一个节点的指针指向当前节点
            pre = cur;
            // 把当前节点的指针指向下一个节点
            cur = next;
        }
        return pre;
    }

}