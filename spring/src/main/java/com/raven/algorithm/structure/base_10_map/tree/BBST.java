package com.raven.algorithm.structure.base_10_map.tree;

import java.util.Comparator;

/**
 * Description:
 * date: 2022/7/17 13:48
 * 平衡二叉搜索树
 * @author raven
 */
public class BBST<E>  extends BST<E> {
    public BBST() {
    }

    public BBST(Comparator<E> comparator) {
        super(comparator);
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
    protected void rotateLeft(Node<E> grand) {
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
    protected void rotateRight(Node<E> grand) {
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
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
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
    protected void rotate(Node<E> r, //子树的根节点
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

        // 设置e-f-g
        f.left = e;
        if (e != null) {
            e.parent = f;
        }
        f.right = g;
        if (g != null) {
            g.parent = f;
        }

        // 设置b-d-f
        d.left = b;
        b.parent = d;
        d.right = f;
        f.parent = d;

    }
}