package com.raven.algorithm.exercise.链表;

/**
 * Description:
 * date: 2022/6/11 12:18
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-list/
 *
 * @author raven
 */
public class _83_删除排序链表中的重复元素 {

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        // 链表下一个节点为空 则不需要再比较
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                // 把当前节点指向下一个节点的下一个节点
                cur.next = cur.next.next;
            }else {
                // 移动当前节点的指针
                cur = cur.next;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode();
        ListNode head1 = new ListNode();
        ListNode head2 = new ListNode();
        ListNode head3 = new ListNode();
        ListNode head4 = new ListNode();
        head.next = head1;
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = null;
        head.val = 2;
        head1.val = 2;
        head2.val = 2;
        head3.val = 2;
        head4.val = 2;
        System.out.println(deleteDuplicates(head));
    }
}