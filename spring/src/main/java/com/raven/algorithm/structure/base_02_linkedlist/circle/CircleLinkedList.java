package com.raven.algorithm.structure.base_02_linkedlist.circle;

import com.raven.algorithm.structure.base_02_linkedlist.AbstractList;

/**
 * Description:
 * date: 2022/6/8 21:53
 * 双向循环链表
 *
 * @author raven
 */
public class CircleLinkedList<E> extends AbstractList<E> {

    private Node<E> first;
    private Node<E> last;
    // 处理约瑟夫问题 增加新的指针 指向当前节点
    private Node<E> current;

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
            // 新增的节点的next指向oldFirst prev指向之前的last
            // last 指向新增的节点
            last = new Node<>(first, oldLast, element);
            // 当前为添加第一个元素
            if (oldLast == null) {
                // 当前元素的prev,last 指向当前元素
                last.prev = last;
                last.next = last;
                // 如果之前一个元素都没有 现在添加的就是第一个元素 则first和last一起指向新增的元素
                first = last;
            } else {
                // 之前的最后一个节点 的next指向新的last
                oldLast.next = last;
                // 之前的第一个节点的prev 指向新的节点（新的节点已经为last节点）
                first.prev = last;
            }
        } else {
            // 在链表的中间添加节点
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            // 新增的节点的next指向next节点 新增的阶段的prev指向next的prev
            Node<E> node = new Node<>(next, prev, element);
            // prev的next指向当前节点，next的prev指向当前节点
            next.prev = node;
            prev.next = node;
            // index = 0 size <>0;
            if (index == 0) {
                // index == 0  fist 指向当前节点
                first = node;
            }
        }
        size++;
    }


    @Override
    public E remove(int index) {
        // index = 0 size = 0 时抛出异常
        rangeCheck(index);
        // 删除指定index 的节点
        return remove(node(index));
    }

    /**
     * 删除指定节点
     *
     * @param node
     * @return
     */
    private E remove(Node<E> node) {
        // 只有一个元素 进行删除 断开first和last
        if (size == 1) {
            first = null;
            last = null;
        } else {
            Node<E> prev = node.prev;
            Node<E> next = node.next;
            prev.next = next;
            next.prev = prev;
            // 删除第一个元素，first指向新的原来next
            if (node == first) {
                first = next;
            }
            // 删除最后一个元素, last指向原来的prev
            if (node == last) {
                last = prev;
            }
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

    /**
     * 让current指向头节点first
     */
    public void reset() {
        current = first;
    }

    /**
     * 让current往后走一步
     *
     * @return
     */
    public E next() {
        if (current == null) {
            return null;
        }
        current = current.next;
        return current.element;
    }

    /**
     * 删除current指向的节点,删除成功后让current指向下一个节点
     *
     * @return
     */
    public E remove() {
        if (current == null) {
            return null;
        }
        // 获取下一个节点
        Node<E> next = current.next;
        // 删除当前节点
        E element = remove(current);
        // 如果删除完节点个数为0 则current指向null 因为循环列表最后一个元素的next是指向自己的
        if (size == 0){
            current = null;
        }else {
            current = next;
        }
        return element;
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