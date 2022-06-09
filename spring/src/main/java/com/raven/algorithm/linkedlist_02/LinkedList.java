package com.raven.algorithm.linkedlist_02;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Node;

/**
 * Description:
 * date: 2022/6/8 21:53
 *
 * @author raven
 */
public class LinkedList<E> extends AbstractList<E> {

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
        if (index == 0) {
            //  新创建节点的next 指向之前的first
            //  first再指向新节点
            first = new Node<>(first, element);
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
            // 当index 为0 时，将first指向当前节点的下一个节点
            first = node.next;
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
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
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
                node = first.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) {
                    return i;
                }
                node = first.next;
            }
        }
        return NOT_FOUND_ELEMENT;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedList{size=").append(size).append(", elements=[");
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
        rangeCheckForAdd(index);
        // 从第一个开始往下找, 指定index的前一个的next 就是指定index对应的节点
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = first.next;
        }
        return node;
    }

}