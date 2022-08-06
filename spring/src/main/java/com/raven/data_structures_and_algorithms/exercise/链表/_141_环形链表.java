package com.raven.data_structures_and_algorithms.exercise.链表;

/**
 * Description:
 * date: 2022/6/11 11:54
 * https://leetcode.cn/problems/linked-list-cycle/
 *
 * @author raven
 */
public class _141_环形链表 {

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        // 利用快慢指针的方式判断是否有环
        // 如果慢指针能够和快指针相遇则证明有环
        ListNode slow = head;
        ListNode fast = head.next;
        // 快指针走完链表
        while (fast != null && fast.next != null) {
            //慢指针
            slow = slow.next;
            // 快指针
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}