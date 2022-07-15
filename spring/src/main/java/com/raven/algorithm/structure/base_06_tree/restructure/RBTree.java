package com.raven.algorithm.structure.base_06_tree.restructure;

import org.w3c.dom.Node;

import java.util.Comparator;

/**
 * Description:
 * date: 2022/7/14 21:35
 *
 * @author raven
 */
public class RBTree<E> extends BST<E> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree() {
        this(null);
    }

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }


    /**
     * 染色接口 将node的颜色修改为指定颜色
     *
     * @param node
     * @param color
     * @return
     */
    private Node<E> color(Node<E> node, boolean color) {
        if (node == null) {
            return node;
        }
        ((RBNode<E>) node).color = color;
        return node;
    }

    /**
     * 将节点的颜色修改为红色
     * @param node
     * @return
     */
    private Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    /**
     *  将节点的颜色修改为黑色
     * @param node
     * @return
     */
    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    /**
     * 判断节点颜色是不是黑色
     * @param node
     * @return
     */
    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    /**
     * 判断节点颜色是不是红色
     * @param node
     * @return
     */
    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

    /**
     * 返回节点的颜色
     *
     * @param node
     * @return
     */
    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>) node).color;
    }

    @Override
    protected void afterAdd(Node<E> node) {

    }

    @Override
    protected void afterRemove(Node<E> node) {
        super.afterRemove(node);
    }

    private static class RBNode<E> extends Node<E> {
        private boolean color = RED;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }
    }
}