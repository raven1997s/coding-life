package com.raven.data_structures_and_algorithms.structure.base_02_linkedlist.circle;

import com.raven.data_structures_and_algorithms.structure.base_02_linkedlist.AbstractList;

/**
 * Description:
 * date: 2022/6/8 21:53
 * 单向循环列表
 *
 * @author raven
 */
public class CircleSingleLinkedList<E> extends AbstractList<E> {

    private Node<E> first;

    static class Node<E> {
        public Node<E> next;
        public E element;

        public Node(Node<E> next, E element) {
            this.next = next;
            this.element = element;
        }
    }

    @Override
    public void add(E element, int index) {
        rangeCheckForAdd(index);
        if (index == 0) {
            // 新节点的next指向first
            Node<E> newFirst = new Node<>(first, element);
            // 取出最后一个节点，让其的next指向新的节点 如果只有一个元素 则最后一个节点为新的节点
            Node<E> last = size == 0 ? newFirst : node(size - 1);
            //  新创建节点的next 指向之前的first
            last.next = newFirst;
            //  first再指向新节点
            first = newFirst;
        } else {
            // 获取当前index前一个节点
            Node<E> pre = node(index - 1);
            // 前一个节点的next 改为当前节点的next
            // 前一个节点的next指向当前节点
            pre.next = new Node<>(pre.next, element);
        }
        size++;
    }


    @Override
    public E remove(int index) {
        rangeCheck(index);
        Node<E> node = first;
        if (index == 0) {
            if (size == 1) {
                first = null;
            } else {
                // 获取最后一个节点 必须先获取last node method会用到first遍历获取每一个元素
                Node<E> last = node(size - 1);
                // 当index 为0 时，将first指向当前节点的下一个节点
                // fist 指向之前first的next
                first = first.next;
                // 最后一个节点的next 指向新的first
                last.next = first;
            }
        } else {
            // 获取指定index的前一个节点
            Node<E> pre = node(index - 1);
            // 返回被删除的当前节点，也就是当前节点的前一个节点的next
            node = pre.next;
            // 将前一个节点的next指向当前节点的的下一个节点。也就是当前节点的next
            pre.next = node.next;
        }
        size--;
        return node.element;
    }

    /**
     * 找到指定索引的节点，并返回对应的元素
     *
     * @param index
     * @return
     */
    @Override
    public E get(int index) {
        /**
         * 需要通过node查找到前一个节点 可能一次找到，也可能需要遍历完整个链表
         * 时间复杂度：
         * 最好：O(1)
         * 最坏：O(n)
         * 平均：O(n)
         */
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        /**
         * 需要通过node查找到前一个节点 可能一次找到，也可能需要遍历完整个链表
         * 时间复杂度：
         * 最好：O(1)
         * 最坏：O(n)
         * 平均：O(n)
         */
        // 获取当前索引对应的节点
        Node<E> node = node(index);
        // 获取当前节点的元素
        E old = node.element;
        // 设置当前节点的元素为新的元素
        node.element = element;
        return old;
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
    }

    @Override
    public int indexOf(E element) {
        Node<E> node = first;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == (element)) {
                    return i;
                }
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) {
                    return i;
                }
                node = node.next;
            }
        }
        return NOT_FOUND_ELEMENT;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SingleLinkedList{size=").append(size).append(", elements=[");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(node.element);
            node = node.next;
        }
        sb.append("]}");
        return sb.toString();
    }

    /**
     * 获取index的节点
     *
     * @param index
     * @return
     */
    private Node<E> node(int index) {
        rangeCheck(index);
        // 从第一个开始往下找, 指定index的前一个的next 就是指定index对应的节点
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

}