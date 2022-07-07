package com.raven.algorithm.structure.base_06_tree.restructure;

import org.w3c.dom.Node;

import java.util.Comparator;

/**
 * Description:
 * date: 2022/7/6 20:24
 * 添加元素后平衡二叉树
 * 左旋，右旋（LL，LR，RR，LL）的理解
 * 统一旋转
 * 删除元素后平衡二叉树
 * @author raven
 */
public class AVLTree<E> extends BST<E> {


    public AVLTree() {
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * 添加可能会让所以祖父节点失衡
     * @param node
     */
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
                rebalance2(node);
                break;
            }
        }
    }

    /**
     * 删除只可能会让父节点或祖先节点失衡
     * @param node
     */
    @Override
    protected void afterRemove(Node<E> node) {
        // 从节点循环向上，逐个判断当前节点是否失衡，如果失衡，则进行恢复平衡
        while ((node = node.parent) != null) {
            // 如果是平衡节点
            if (isBalanced(node)) {
                // 设置节点高度
                updateHeight(node);
            } else {
                // 恢复平衡
                rebalance2(node);
            }
        }
    }

    /**
     * 恢复平衡
     *
     * @param grand 是高度最低的不平衡的节点
     */
    private void rebalance2(Node<E> grand) {
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
                rotate(grand, node.left, node, node.right, parent, parent.right, grand, grand.right);
            } else { //LR 需要先RR左旋parent节点 再LL右旋grand节点
                rotate(grand, parent.left, parent, node.left, node, node.right, grand, grand.right);
            }
        } else {
            // node是parent的右子树
            if (node.isRightChild()) {// RR 左旋grand节点
                rotate(grand, grand.left, grand, parent.left, parent, node.left, node, node.right);
            } else { // RL 需要先LL右旋parent节点， 再RR左旋grand节点
                rotate(grand, grand.left, grand, node.left, node, node.right, parent, parent.right);
            }
        }
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    /**
     * @param r 子树的根节点
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @param f
     * @param g
     */
    private void rotate(Node<E> r, //子树的根节点
                        Node<E> a, Node<E> b, Node<E> c,
                        Node<E> d,
                        Node<E> e, Node<E> f, Node<E> g) {

        // 让d变为子树的根节点
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else { // r为root节点
            root = d;
        }

        // 设置a-b-c
        b.left = a;
        if (a != null) {
            a.parent = b;
        }
        b.right = c;
        if (c != null) {
            c.parent = b;
        }
        // 更新b的节点高度
        updateHeight(b);

        // 设置e-f-g
        f.left = e;
        if (e != null) {
            e.parent = f;
        }
        f.right = g;
        if (g != null) {
            g.parent = f;
        }
        // 更新f的节点高度
        updateHeight(f);

        // 设置b-d-f
        d.left = b;
        b.parent = d;
        d.right = f;
        f.parent = d;
        // 更新d
        updateHeight(d);
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
     * @param grand
     */
    private void rotateLeft(Node<E> grand) {
        // RR
        // 因为是左旋，所以parent一定是grand的right子树
        Node<E> parent = grand.right;
        // 获取要旋转位置的parent的左子树
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
        //
        //// parent为子树的根节点 设置parent的parent
        //// 如果grand是父节点的左子树，则让parent改为父节点的左子树
        //parent.parent = grand.parent;
        //if (grand.isLeftChild()) {
        //    grand.parent.left = parent;
        //} else if (grand.isRightChild()) {
        //    grand.parent.right = parent;
        //} else { // grand是root节点
        //    root = parent;
        //}
        //
        //// 设置child的parent
        //if (child != null) {
        //    child.parent = grand;
        //}
        //// 设置grand的父节点
        //grand.parent = parent;
        //
        //// 更新高度
        //updateHeight(grand);
        //updateHeight(parent);
    }

    /**
     * 右旋
     *
     * @param grand
     */
    private void rotateRight(Node<E> grand) {
        // LL
        // 右旋，parent一定是grand的左子树
        Node<E> parent = grand.left;
        // 获取要进行旋转的parent的right
        Node<E> child = parent.right;
        // 设置旋转交换
        grand.left = child;
        parent.right = grand;
        //旋转后设置属性
        afterRotate(grand, parent, child);
    }

    /**
     * @param grand  祖父节点
     * @param parent 父节点
     * @param child  子节点
     */
    private void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        // 设置parent的parent
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            root = parent;
        }

        // 设置child的parent
        if (child != null) {
            child.parent = grand;
        }

        // 设置grand的parent
        grand.parent = parent;

        // 更新高度
        updateHeight(grand);
        updateHeight(parent);
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

        @Override
        public String toString() {
            String str;
            if (parent == null) {
                str = "_p(null)";
            } else {
                str = "_p(" + parent.element + ")";
            }
            return element + str + "h(" + height + ")";
        }
    }

}