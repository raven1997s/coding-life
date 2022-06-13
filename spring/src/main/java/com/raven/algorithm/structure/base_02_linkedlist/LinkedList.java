package com.raven.algorithm.structure.base_02_linkedlist;

/**
 * Description:
 * date: 2022/6/8 21:53
 * 双向链表
 * @author raven
 */
public class LinkedList<E> extends AbstractList<E> {

    private Node<E> first;
    private Node<E> last;

    static class Node<E> {
        public Node<E> next;
        public Node<E> prev;
        public E element;

        public Node(Node<E> next, Node<E> prev, E element) {
            this.next = next;
            this.prev = prev;
            this.element = element;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "next=" + next +
                    ", prev=" + prev +
                    ", element=" + element +
                    '}';
        }
    }

    @Override
    public void add(E element, int index) {
        rangeCheckForAdd(index);
        // todo
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
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedList{size=").append(size).append(", elements=[");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(node);
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
        Node<E> node;
        // 如果index 大于等于链表长度的一半 则从右边开始查找节点会更快
        if (index >= size >> 1){
            node = last;
            // 从最后一个开始往前找
            for (int i = size -1 ; i > index; i--) {
                node = node.prev;
            }
        }else {
            // 从第一个开始往下找, index的next 就是指定index对应的节点
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        }

        return node;
    }

}