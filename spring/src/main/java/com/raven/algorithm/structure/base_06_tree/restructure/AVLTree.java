package com.raven.algorithm.structure.base_06_tree.restructure;

import org.w3c.dom.Node;

import java.util.Comparator;

/**
 * Description:
 * date: 2022/7/6 20:24
 *
 * @author raven
 */
public class AVLTree<E> extends BST<E> {


    public AVLTree() {
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        // 根据平衡因子判断是否需要平衡二叉树
        // 循环判断节点的父节点是否需要调整，主要依据为父节点的左右子树高度是否在平衡因子范围内
        while ((node = node.parent) != null) {
            // 如果是平衡节点
            if (isBalanced(node)) {
                // 设置节点高度
                updateHeight(node);
            } else {
                // 恢复平衡
                rebalance(node);
                break;
            }
        }
    }

    /**
     * 恢复平衡
     *
     * @param grand 是高度最低的不平衡的节点
     */
    private void rebalance(Node<E> grand) {
        // 获取parent节点 以及node节点
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        // 如果parent是grand是左子树
        if (parent.isLeftChild()) {
            // node是parent的左子树
            if (node.isLeftChild()) { //LL 需要右旋Grand节点
                rotateRight(grand);
            } else { //LR 需要先RR左旋parent节点 再LL右旋grand节点
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else {
            // node是parent的右子树
            if (node.isRightChild()) {// RR 左旋grand节点
                rotateLeft(grand);
            } else { // RL 需要先LL右旋parent节点， 再RR左旋grand节点
                rotateRight(parent);
                rotateLeft(grand);
            }
        }
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    /**
     * 左旋与右旋
     * G：grand节点 P：parent节点 N：node节点
     * -----------------LL-----------------------
     * LL ：P为G的左子树，N为P的左子树 在N下添加元素
     * 在N下添加元素，导致G的左子树高度高于右子树高度，所以需要右旋G节点，让左边高度降低(哪边高降低哪边，向反方向旋转)
     * G.left = P.right P.right = G P设置为子树的根节点
     * -----------------RR-----------------------
     * RR：P为G的右子树，N为P的右子树 在N下添加元素
     * 在N下添加元素，导致G的右子树高度高于左子树高度，所以需要左旋G节点，让右边高度降低(哪边高降低哪边，向反方向旋转)
     * G.right = p.left P.left = G P设置为子树的根节点
     * -----------------LR-----------------------
     * LR：P为G的左子树，N为P的右子树，在N下添加元素
     * 在N下添加元素，从下往上调整。
     * 首先调整P，N在P的右子树，将P左旋，P.right = N.left  N.left = P  N为子树根节点
     * 然后调整G，此时N在G的左子树，将G右旋，G.left = N.right N.right = G  N为整个子树的根节点
     * -----------------RL-----------------------
     * RL：P为G的右子树，N为P的左子树，在N下添加元素
     * 在N下添加元素，从下往上调整。
     * 首先调整P，N在P的左子树，将P右旋，P.left = N.right N.right = P N为子树根节点
     * 然后调整G，此时N在G的右子树，将G左旋，G.right = N.left N.left = G N为整个子树的根节点
     */
    /**
     * 左旋
     *
     * @param node
     */
    private void rotateLeft(Node<E> node) {

    }

    /**
     * 右旋
     *
     * @param node
     */
    private void rotateRight(Node<E> node) {

    }

    /**
     * 如果平衡因子的决定值大于等于1，则该节点不平衡
     *
     * @param node
     * @return 判断AVL节点是否为平衡的节点
     */
    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }

    /**
     * 设置节点的高度
     *
     * @param node
     */
    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }

    /**
     * AVL树特有height属性，所以extendNode节点，添加属性元素
     *
     * @param <E>
     */
    private static class AVLNode<E> extends Node<E> {

        // 记录AVL节点的高度，用于添加节点时判断是否需要调整旋转二叉树
        private int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        /**
         * 返回AVL树的平衡因子
         *
         * @return 左右子树的高度差
         */
        public int balanceFactor() {
            // 因为node子节点属性中没有height属性，所以需要子节点强制转换为AVLNode
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            return leftHeight - rightHeight;
        }

        /**
         * 修改节点的高度
         * 节点的高度等于左右子树中高度高的节点高度+1
         */
        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            height = Math.max(leftHeight, rightHeight) + 1;
        }

        /**
         * 返回AVLNode左右子树中高度高的节点
         * 左右子树高度相等时 如果当前AVLNode是父节点的左子树，则返回AVLNode的左子树，否则返回AVLNode的右子树(减少旋转次数)
         *
         * @return
         */
        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            //返回AVLNode左右子树中高度高的节点
            if (leftHeight > rightHeight) {
                return left;
            }
            if (leftHeight < rightHeight) {
                return right;
            }
            // 左右子树高度相等，根据AVLNode是否为父节点的左子树，如果是则返回AVLNode的左子树
            return isLeftChild() ? left : right;
        }
    }

}