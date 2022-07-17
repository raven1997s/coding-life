package com.raven.algorithm.structure.base_06_tree.restructure;

import org.w3c.dom.Node;

import java.util.Comparator;

/**
 * Description:
 * date: 2022/7/14 21:35
 *
 * @author raven
 */
public class RBTree<E> extends BBST<E> {
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
     *
     * @param node
     * @return
     */
    private Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    /**
     * 将节点的颜色修改为黑色
     *
     * @param node
     * @return
     */
    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    /**
     * 判断节点颜色是不是黑色
     *
     * @param node
     * @return
     */
    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    /**
     * 判断节点颜色是不是红色
     *
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

    /**
     * 添加时默认新添加的节点为红色，根据具体情况(12种)再进行变色 旋转
     *
     * @param node
     */
    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;
        // 如果parent为null，添加的是根节点或者上溢到达了根节点
        if (parent == null) {
            // 将根节点染为黑色
            black(node);
            return;
        }

        // 如果父节点是黑色的，满足红黑树的五大原则，直接返回
        if (isBlack(parent)) {
            return;
        }

        // 拿到叔父节点，来判断是哪一种情况
        Node<E> uncle = parent.sibling();
        Node<E> grand = red(parent.parent);
        // 叔父节点是红色，添加节点后需要进行上溢操作 (类比四阶B树此时超级节点已经有三个元素，所以需要上溢处理，父节点，祖父节点，叔父节点)
        if (isRed(uncle)) {
            // 将父节点和叔父节点染为黑色
            black(parent);
            black(uncle);
            // 祖父节点向上溢出(染为红色，当成新的节点加入处理)（上面已经统一处理）
            afterAdd(grand);
            return;
        }

        // 叔父节点是黑色,不需要上溢(类比四阶b树此时超级节点元素<3 )
        if (parent.isLeftChild()) {// L
            if (node.isLeftChild()) { //LL
                // 父节点染为黑色 祖父节点染为红色（上面已经统一处理）
                // 祖父节点右旋 父节点变为超级节点中的parent
                black(parent);
            } else { //LR
                // 自己染为黑色 //祖父节点染为红色（上面已经统一处理）
                // 先将父节点RR左旋，再将祖父节点LL右旋
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else { //R
            if (node.isRightChild()) { // RR
                // 父节点染为黑色 祖父节点染为红色 （上面已经统一处理）
                // 祖父节点RR左旋
                black(parent);
            } else { //RL
                // 自己染为黑色 祖父节点染为红色 （上面已经统一处理）
                // 现将父节点LL右旋 再将祖父节点RR左旋
                black(node);
                rotateRight(parent);
            }
            rotateLeft(grand);
        }

    }

    @Override
    protected void afterRemove(Node<E> node) {
        super.afterRemove(node);
    }

    /**
     * 二叉树添加节点时指定添加的节点为红黑树节点
     *
     * @param element
     * @param parent
     * @return
     */
    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element, parent);
    }

    private static class RBNode<E> extends Node<E> {
        private boolean color = RED;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(String.valueOf(element));
            if (color == RED){
                sb.append("_red");
            }
            return sb.toString();
        }
    }
}