package com.raven.algorithm.exercise.链表;

/**
 * Description:
 * date: 2022/6/9 22:37
 * https://leetcode.cn/problems/reverse-linked-list/
 *
 * @author raven
 */
public class _206_反转链表 {

    // TODO(raven): 2022/6/9  ??
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        // 当前节点的下一个节点的next 指向当前节点，
        head.next.next = head;
        // 最后一个节点指向null
        head.next = null;
        return newHead;
    }
}