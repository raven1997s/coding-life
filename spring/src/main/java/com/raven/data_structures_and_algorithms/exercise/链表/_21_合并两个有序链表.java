package com.raven.data_structures_and_algorithms.exercise.链表;


import lombok.val;

/**
 * Description:
 * date: 2022/8/11 11:54
 * https://leetcode.cn/problems/merge-two-sorted-lists/
 *
 * @author raven
 */
public class _21_合并两个有序链表 {
    /**
     * 递归调用
     *
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else if (list1.val < list2.val) {
            list1.next = mergeTwoLists2(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists2(list1, list2.next);
            return list2;
        }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 创建节点默认值为-1 用于最终返回
        ListNode preHead = new ListNode(-1);
        // prev指针用于记录链表元素
        ListNode prev = preHead;
        while (list1 != null && list2 != null) {
            // 如果list1的数值小于list2
            // 则把prev的下一个指向list1
            // 把list1的指针后移一位
            if (list1.val <= list2.val) {
                prev.next = list1;
                list1 = list1.next;
            } else {
                prev.next = list2;
                list2 = list2.next;
            }
            //
            prev = prev.next;
        }
        prev.next = list1 == null ? list2 : list1;
        // 从preHead.next开始才是真正节点的数据
        return preHead.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode();
        ListNode head1 = new ListNode();
        ListNode head2 = new ListNode();

        ListNode head3 = new ListNode();
        ListNode head4 = new ListNode();
        head.next = head1;
        head1.next = head2;
        head2.next = null;
        head3.next = head4;
        head4.next = null;
        head.val = 0;
        head1.val = 1;
        head2.val = 2;
        head3.val = 3;
        head4.val = 4;
        final ListNode node = mergeTwoLists(head, head3);
        System.out.println(node);
    }
}