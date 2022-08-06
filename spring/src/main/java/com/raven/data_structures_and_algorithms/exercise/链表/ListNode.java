package com.raven.data_structures_and_algorithms.exercise.链表;

/**
 * Description:
 * date: 2022/6/9 22:41
 *
 * @author raven
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}