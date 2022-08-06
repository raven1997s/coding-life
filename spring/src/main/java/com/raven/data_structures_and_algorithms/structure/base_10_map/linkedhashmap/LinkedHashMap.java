package com.raven.data_structures_and_algorithms.structure.base_10_map.linkedhashmap;

import java.util.Objects;

/**
 * Description:
 * date: 2022/7/27 21:41
 *
 * @author raven
 */
public class LinkedHashMap<K, V> extends HashMap<K, V> {

    /**
     * 指向第一个节点
     */
    private LinkedNode<K, V> first;
    /**
     * 指向最后一个节点
     */
    private LinkedNode<K, V> last;


    /**
     * 删除节点后将维护的链表线断掉
     *
     * @param removeNode
     */
    @Override
    protected void afterRemove(Node<K, V> willNode, Node<K, V> removeNode) {
        if (removeNode == null) {
            return;
        }

        LinkedNode<K, V> node1 = (LinkedNode<K, V>) willNode;
        LinkedNode<K, V> node2 = (LinkedNode<K, V>) removeNode;

        // 删除度为二的节点时，真正删除的节点时前驱or后继节点，需要修复他们在链表中的位置
        if (node1 != node2) {
            // 交换prev
            // 存储想要被删除节点的前一个节点
            LinkedNode<K, V> tempNode = node1.prev;
            // 将想要被删除的节点的prev 指向要被删除节点的prev
            node1.prev = node2.prev;
            // 将要被删除节点的前一个prev 指向想要被删除节点的prev
            node2.prev = tempNode;
            // 如果想要被删除节点的前一个为null 则他为first
            if (node1.prev == null) {
                first = node1;
            } else {
                node1.prev.next = node1;
            }
            if (node2.prev == null) {
                first = node1;
            } else {
                node2.prev.next = node1;
            }

            // 交换next
            tempNode = node1.next;
            node1.next = node2.next;
            node2.next = tempNode;
            if (node1.next == null) {
                last = node1;
            } else {
                node1.next.prev = node1;
            }
            if (node2.next == null) {
                last = node1;
            } else {
                node2.next.prev = node2;
            }
        }

        //  获取节点前一个节点 以及后一个节点
        LinkedNode<K, V> prev = node2.prev;
        LinkedNode<K, V> next = node2.next;

        // 如果前一个节点为null 说明删除的是链表的首节点
        if (prev == null) {
            first = next;
        } else {
            // 将前一个节点的next 指向当前节点的下一个节点
            prev.next = next;
        }

        // 如果节点后一个节点为null 说明删除的是链表的尾节点
        if (next == null) {
            last = prev;
        } else {
            // 将下一个节点的prev 指向之前的prev
            next.prev = prev;
        }
    }

    /**
     * 重写containsValue 通过 链表遍历按照添加顺序寻找
     *
     * @param value
     * @return
     */
    @Override
    public boolean containsValue(V value) {
        LinkedNode<K, V> node = first;
        while (node != null) {
            if (Objects.equals(node.value, value)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    /**
     * 重写遍历
     *
     * @param visitor
     */
    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (visitor == null) {
            return;
        }
        LinkedNode<K, V> node = first;
        while (node != null) {
            if (visitor.visit(node.key, node.value)) {
                return;
            }
            node = node.next;
        }
    }

    @Override
    public void clear() {
        super.clear();
        // 清空的时候将last first清空
        last = null;
        first = null;
    }

    @Override
    protected Node<K, V> createNode(K key, V value, Node<K, V> parent) {
        // 创建节点时创建为linkedNode
        LinkedNode<K, V> node = new LinkedNode<>(key, value, parent);
        // 指定node的前一个节点和下一个节点
        // 如果头节点为空 将节点放到第一个节点
        if (first == null) {
            // first last 都指向第一个节点
            first = last = node;
        } else {
            // 将节点放到链表最后面
            last.next = node;
            node.prev = last;
            last = node;
        }
        return node;
    }

    /**
     * 自定义linkedNode
     *
     * @param <K>
     * @param <V>
     */
    private static class LinkedNode<K, V> extends Node<K, V> {
        // 用来封装前一个节点和下一个节点
        public LinkedNode<K, V> prev;
        public LinkedNode<K, V> next;

        public LinkedNode(K key, V value, Node<K, V> parent) {
            super(key, value, parent);
        }
    }
}