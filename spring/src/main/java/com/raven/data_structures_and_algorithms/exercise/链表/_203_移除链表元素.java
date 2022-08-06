package com.raven.data_structures_and_algorithms.exercise.链表;

/**
 * Description:
 * date: 2022/6/11 12:17
 * https://leetcode.cn/problems/remove-linked-list-elements/
 *
 * @author raven
 */
public class _203_移除链表元素 {
    public static ListNode removeElements(ListNode head, int val) {
        if (head == null){
            return head;
        }
        // 创建一个虚拟节点
        ListNode pre = new ListNode(val - 1);
        // 虚拟节点指向当前节点
        pre.next = head;
        // 利用temp删除值相同的节点
        ListNode temp = pre;
        while (temp.next != null) {
            // 从头节点开始判断
            if (temp.next.val == val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        // 返回处理好后的head
        return pre.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode();
        ListNode head1 = new ListNode();
        ListNode head2 = new ListNode();
        ListNode head3 = new ListNode();
        ListNode head4 = new ListNode();
        head.next = head1;
        head1.next = null;
        //head2.next = head3;
        //head3.next = head4;
        //head4.next = null;
        head.val = 2;
        head1.val = 3;
        //head2.val = 2;
        //head3.val = 2;
        //head4.val = 2;
        ListNode listNode = removeElements(head, 2);
        System.out.println(listNode);
    }

}