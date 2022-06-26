package com.raven.algorithm.structure.base_05_binarysearchtree;

import com.raven.algorithm.structure.utils.printer.BinaryTreeInfo;

import java.util.*;

/**
 * Description:
 * date: 2022/6/23 20:29
 * 实现 BinaryTreeInfo 便于打印树
 *
 * @author raven
 */
public class BinarySearchTree<E> implements BinaryTreeInfo {

    private int size;
    // 根节点
    private Node<E> root;

    // 自定义比较器
    private final Comparator<E> comparator;

    /**
     * 使用默认比较器
     */
    public BinarySearchTree() {
        this(null);
    }

    /**
     * 使用自定义比较器
     *
     * @param comparator
     */
    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

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
                // 节点元素和树上元素相等 重新赋值
                node.element = element;
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
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> myNode = (Node<E>) node;
        String str;
        if (myNode.parent == null) {
            str = "_p(null)";
        } else {
            str = "_p(" + myNode.parent.element + ")";
        }
        return myNode.element + str;
    }

    /**
     * 前序遍历
     * 先遍历root节点 再遍历左子节点 再遍历右子节点，遵循这一原则遍历完所有元素
     */
    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    private void preOrderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        System.out.println(node.element);
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    /**
     * 中序遍历
     * 先遍历左子节点 再遍历parent(root)节点 最后遍历右子节点
     * 二叉搜索树进行中序遍历会从小到大输出元素
     */
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.left);
        System.out.println(node.element);
        inOrderTraversal(node.right);
    }

    /**
     * 后序遍历
     * 先遍历左子节点 再遍历右子节点，再遍历root节点 遵循这一原则遍历完所有元素
     */
    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    private void postOrderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.println(node.element);
    }

    /**
     * 层序遍历
     * 从根节点开始，一层一层从左到右挨个遍历元素
     */
    public void levelOrderTraversal() {
        if (root == null) {
            return;
        }
        // 层序遍历 利用队列存储元素顺序，将元素逐层挨个放入队列，后逐个取出。
        Queue<Node<E>> queue = new LinkedList<>();
        // 将根节点入队
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 取出节点输出
            Node<E> top = queue.poll();
            System.out.println(top.element);
            // 存储下一层元素 不为空才需要存储
            Node<E> left = top.left;
            if (left != null) {
                queue.add(left);
            }
            Node<E> right = top.right;
            if (right != null) {
                queue.add(right);
            }
        }
    }

    /**
     * @param node
     */
    private void levelOrderTraversal(Node<E> node) {

    }


    // 1 : e1 > e2  0 : e1 = e2  -1 : e1 < e2

    /**
     * 对象比较方式：
     * planA：写死比较方式 pass 比较的纬度和方式时不确定的，不能使用简单的固定的数值类型的的比较
     * planB：定义接口comparable  BinarySearchTree继承接口 所有的树创建时指定比较方式  pass  同一对象不能右多种 比较的方式
     * planC: 定义接口comparator   BinarySearchTree 实现接口 compartore 创建树对象时，比较器由外部提供
     * 1 : e1 > e2  0 : e1 = e2  -1 : e1 < e2
     *
     * @param e1
     * @param e2
     * @return
     */
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        // 如果没有传比较器 则使用默认比较器 即创建树时 对象必须实现(Comparable<E>)接口 指定比较器
        return ((Comparable<E>) e1).compareTo(e2);
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }
}