package com.raven.algorithm.structure.base_02_linkedlist;

/**
 * Description:
 * date: 2022/6/8 21:53
 * 双向链表
 *
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
                    "next=" + (next == null ? "null" : next.element) +
                    ", prev=" + (prev == null ? "null" : prev.element) +
                    ", element=" + element +
                    '}';
        }
    }

    @Override
    public void add(E element, int index) {
        rangeCheckForAdd(index);
        // index = 0 size = 0 在链表最后添加节点
        // index <> 0 index = size 在链表最后添加节点
        if (index == size) {
            Node<E> oldLast = last;
            // 新增的节点的next指向null prev指向之前的last
            // last 指向新增的节点
            last = new Node<>(null, oldLast, element);
            // 之前的最后一个节点 的next指向新的last
            if (oldLast == null) {
                // 如果之前一个元素都没有 现在添加的就是第一个元素 则first和last一起指向新增的元素
                first = last;
            } else {
                oldLast.next = last;
            }
        } else {
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            // 在链表的中间添加节点
            // 新增的节点的next指向next节点 新增的阶段的prev指向next的prev
            Node<E> node = new Node<>(next, prev, element);
            next.prev = node;
            // index = 0 size <>0;
            if (prev == null) {
                // index == 0  fist 指向当前节点
                first = node;
            } else {
                // prev的next指向当前节点，next的prev指向当前节点
                prev.next = node;
            }
        }
        size++;
    }


    @Override
    public E remove(int index) {
        // index = 0 size = 0 时抛出异常
        rangeCheck(index);
        Node<E> node = node(index);
        Node<E> prev = node.prev;
        Node<E> next = node.next;
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
        }
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
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
        last = null;
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
        if (index >= (size >> 1)) {
            node = last;
            // 从最后一个开始往前找
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        } else {
            // 从第一个开始往下找, index的next 就是指定index对应的节点
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        }

        return node;
    }

}