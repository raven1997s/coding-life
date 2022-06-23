package com.raven.algorithm.structure.base_05_binarysearchtree;

import com.raven.algorithm.structure.utils.printer.BinaryTreeInfo;

/**
 * Description:
 * date: 2022/6/23 20:29
 * 继承comparable接口 规范树的范型的对象必须自定义实现比较函数
 * @author raven
 */
public class BinarySearchTreeExtentComparable<E extends Comparable<E> > implements BinaryTreeInfo {

    private int size;
    // 根节点
    private Node<E> root;

    private static class Node<E> {
        E element;
        Node<E> parent;
        Node<E> left;
        Node<E> right;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
    }

    public int size() {
        return size;
    }

    public void clear() {

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E element) {
        // 树上的元素不能为null
        elementNotNullCheck(element);

        // 添加第一个节点
        if (root == null) {
            root = new Node<>(element, null);
            size++;
            return;
        }

        // 添加的不是第一个节点
        Node<E> node = root;
        Node<E> parent = root;
        // 记录比较结果，确定元素添加到父节点的那个方向
        int result = 0;
        // 当节点元素比较到叶子节点，叶子节点没有子节点，比较结束，叶子节点为节点元素节点的父节点
        while (node != null) {
            // 用节点元素和树上的节点元素比较大小
            // 如果节点元素大于树上的节点元素，则继续和节点元素的右节点进行比较
            result = compare(element, node.element);
            // 比较前记录父节点 创建节点时使用
            parent = node;
            if (result > 0) {
                node = node.right;
            } else if (result < 0) {
                node = node.left;
            } else {
                // 节点元素和树上元素相等
                return;
            }
        }

        if (result > 0) {
            parent.right = new Node<>(element, parent);
        } else {
            parent.left = new Node<>(element, parent);
        }
        size++;
    }

    public E remove() {
        return null;
    }

    public boolean contains(E e) {
        return false;
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((BinarySearchTreeExtentComparable.Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((BinarySearchTreeExtentComparable.Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        return ((BinarySearchTreeExtentComparable.Node<E>) node).element;
    }
    // 1 : e1 > e2  0 : e1 = e2  -1 : e1 < e2

    /**
     * 对象比较方式：
     * planA：写死比较方式 pass 比较的纬度和方式时不确定的，不能使用简单的固定的数值类型的的比较
     * planB：定义接口comparable  BinarySearchTree继承接口 所有的树创建时指定比较方式  pass  同一对象不能右多种 比较的方式
     * planC: 定义接口compartore   BinarySearchTree 实现接口 compartore 创建树对象时，比较器由外部提供
     * 1 : e1 > e2  0 : e1 = e2  -1 : e1 < e2
     * @param e1
     * @param e2
     * @return
     */
    private int compare(E e1, E e2) {
        return e1.compareTo(e2);
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }
}