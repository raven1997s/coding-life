package com.raven.algorithm.structure.base_06_tree.avltreeandrbtree;

import java.util.Comparator;

/**
 * Description:
 * date: 2022/7/14 21:35
 * 红黑树也是一种自平衡的二叉搜索树
 * 以前也叫做平衡二叉B树 (Symmetric Binary B-tree)
 * 红黑树必须满足以下 5条性质
 * 1. 节点是 RED 或者 BLACK
 * 2. 根节点是 BLACK
 * 3. 叶子节点（外部节点，空节点）都是 BLACK
 * 4. RED 节点的子节点都是 BLACK
 * 4.1RED 节点的 parent 都是 BLACK
 * 4.2从根节点到叶子节点的所有路径上不能有2 个连续的RED 节点
 * 5.从任一节点到叶子节点的所有路径都包含相同数目的 BLACK 节点
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
                // 父节点染为黑色
                // 祖父节点染为红色（上面已经统一处理）
                // 祖父节点右旋 父节点变为超级节点中的parent
                black(parent);
            } else { //LR
                // 自己染为黑色
                // 祖父节点染为红色（上面已经统一处理）
                // 先将父节点RR左旋，再将祖父节点LL右旋
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else { //R
            if (node.isRightChild()) { // RR
                // 父节点染为黑色
                // 祖父节点染为红色 （上面已经统一处理）
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
    //传递一个个参数
    @Override
    protected void afterRemove(Node<E> node) {
        // 如果要被删除的节点为红色，则直接删除即可
        //if (isRed(node)) {
        //    return;
        //}

        // 俩种情况
        // 1.删除拥有一个red节点的黑色节点  用以替代的子节点是红色
        // 将被替换的节点染为黑色
        // 2.删除红色的叶子节点，复用代码
        if (isRed(node)) {
            black(node);
            return;
        }

        Node<E> parent = node.parent;
        // 删除的是根节点
        if (parent == null) {
            return;
        }

        // 删除黑色叶子节点
        // 找到被删除节点的兄弟
        // 不能通过下面的方式获取节点的兄弟节点 因为是通过isRightChild 来判断是不是兄弟节点，即（this == parent.right;）
        //Node<E> sibling = node.sibling();
        // 但是在删除的时候已经通过下面的代码将节点和parent断掉了
        // 如果node是node父节点的左子树，则将副节点的左子树设置为null
        /* if (node == node.parent.left) {
            node.parent.left = null;
        } else {
            node.parent.right = null;
        }*/
        // 如果被删除的节点是左，则兄弟节点就是右边
        // node.isLeftChild() 防止下溢时递归调用。此时不会断掉父节点指向下溢节点的线，所以要通过node.isLeftChild()判断
        boolean left = parent.left == null || node.isLeftChild();
        Node<E> sibling = left ? parent.right : parent.left;
        if (left) { //被删除的节点在左边，兄弟节点在右边
            // 如果兄弟节点是红色，先把他转为黑色,parent变为黑色，再把parentRR左旋
            if (isRed(sibling)) {
                black(sibling);
                red(parent);
                rotateLeft(parent);
                // 更新兄弟节点。此时的兄弟节点已经变为parent的right
                sibling = parent.right;
            }

            // 如果兄弟节点是黑色。看兄弟节点是否有至少一个红色节点可以借
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有一个红色子节点，父节点要向下和兄弟节点合并 将父节点染为黑色，兄弟节点染为红色
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                // 如果父节点是黑色，把父节点当成被删除的节点处理 （下溢）
                if (parentBlack) {
                    afterRemove(parent);
                }

            } else {
                // 兄弟节点至少有一个红色子节点，向兄弟节点借元素
                // 如果兄弟节点的右边是黑色，兄弟要先LL右旋转，
                if (isBlack(sibling.right)){
                    rotateRight(sibling);
                    //  右旋后，兄弟节点变为了父节点的右边
                    sibling = parent.right;
                }
                // 将兄弟节点染为之前父节点的颜色
                // 兄弟节点的右边染为黑色
                // 父节点染为黑色
                color(sibling,colorOf(parent));
                black(sibling.right);
                black(parent);
                // 父节点RR左旋转
                rotateLeft(parent);
            }
        } else {//  被删除的节点在右边，兄弟节点在右左
            // 如果兄弟节点是红色，先把他转为黑色,parent变为黑色，再把parentLL右旋
            if (isRed(sibling)) {
                black(sibling);
                red(parent);
                rotateRight(parent);
                // 更新兄弟节点。此时的兄弟节点已经变为parent的left
                sibling = parent.left;
            }

            // 如果兄弟节点是黑色。看兄弟节点是否有至少一个红色节点可以借
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有一个红色子节点，父节点要向下和兄弟节点合并 将父节点染为黑色，兄弟节点染为红色
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                // 如果父节点是黑色，把父节点当成被删除的节点处理
                if (parentBlack) {
                    afterRemove(parent);
                }

            } else {
                // 兄弟节点至少有一个红色子节点，向兄弟节点借元素
                // 如果兄弟节点的左边是黑色，兄弟要先RR左旋转，
                //
                if (isBlack(sibling.left)){
                    rotateLeft(sibling);
                    //  左旋后，兄弟节点变为了父节点的左边
                    sibling = parent.left;
                }
                // 将兄弟节点染为之前父节点的颜色
                // 兄弟节点的左边染为黑色
                // 父节点染为黑色
                color(sibling,colorOf(parent));
                black(sibling.left);
                black(parent);
                // 父节点LL右旋转
                rotateRight(parent);
            }

        }


    }

    // 传递俩个参数
    //@Override
    //protected void afterRemove(Node<E> node, Node<E> replacement) {
    //    // 如果要被删除的节点为红色，则直接删除即可
    //    if (isRed(node)) {
    //        return;
    //    }
    //
    //    // 删除拥有一个red节点的黑色节点  用以替代的子节点是红色
    //    // 将被替换的节点染为黑色
    //    if (isRed(replacement)) {
    //        black(replacement);
    //        return;
    //    }
    //
    //    Node<E> parent = node.parent;
    //    // 删除的是根节点
    //    if (parent == null) {
    //        return;
    //    }
    //
    //    // 删除黑色叶子节点
    //    // 找到被删除节点的兄弟
    //    // 不能通过下面的方式获取节点的兄弟节点 因为是通过isRightChild 来判断是不是兄弟节点，即（this == parent.right;）
    //    //Node<E> sibling = node.sibling();
    //    // 但是在删除的时候已经通过下面的代码将节点和parent断掉了
    //    // 如果node是node父节点的左子树，则将副节点的左子树设置为null
    //    /* if (node == node.parent.left) {
    //        node.parent.left = null;
    //    } else {
    //        node.parent.right = null;
    //    }*/
    //    // 如果被删除的节点是左，则兄弟节点就是右边
    //    // node.isLeftChild() 防止下溢时递归调用。此时不会断掉父节点指向下溢节点的线，所以要通过node.isLeftChild()判断
    //    boolean left = parent.left == null || node.isLeftChild();
    //    Node<E> sibling = left ? parent.right : parent.left;
    //    if (left) { //被删除的节点在左边，兄弟节点在右边
    //        // 如果兄弟节点是红色，先把他转为黑色,parent变为黑色，再把parentRR左旋
    //        if (isRed(sibling)) {
    //            black(sibling);
    //            red(parent);
    //            rotateLeft(parent);
    //            // 更新兄弟节点。此时的兄弟节点已经变为parent的right
    //            sibling = parent.right;
    //        }
    //
    //        // 如果兄弟节点是黑色。看兄弟节点是否有至少一个红色节点可以借
    //        if (isBlack(sibling.left) && isBlack(sibling.right)) {
    //            // 兄弟节点没有一个红色子节点，父节点要向下和兄弟节点合并 将父节点染为黑色，兄弟节点染为红色
    //            boolean parentBlack = isBlack(parent);
    //            black(parent);
    //            red(sibling);
    //            // 如果父节点是黑色，把父节点当成被删除的节点处理 （下溢）
    //            if (parentBlack) {
    //                afterRemove(parent, null);
    //            }
    //
    //        } else {
    //            // 兄弟节点至少有一个红色子节点，向兄弟节点借元素
    //            // 如果兄弟节点的右边是黑色，兄弟要先LL右旋转，
    //            if (isBlack(sibling.right)){
    //                rotateRight(sibling);
    //                //  右旋后，兄弟节点变为了父节点的右边
    //                sibling = parent.right;
    //            }
    //            // 将兄弟节点染为之前父节点的颜色
    //            // 兄弟节点的右边染为黑色
    //            // 父节点染为黑色
    //            color(sibling,colorOf(parent));
    //            black(sibling.right);
    //            black(parent);
    //            // 父节点RR左旋转
    //            rotateLeft(parent);
    //        }
    //    } else {//  被删除的节点在右边，兄弟节点在右左
    //        // 如果兄弟节点是红色，先把他转为黑色,parent变为黑色，再把parentLL右旋
    //        if (isRed(sibling)) {
    //            black(sibling);
    //            red(parent);
    //            rotateRight(parent);
    //            // 更新兄弟节点。此时的兄弟节点已经变为parent的left
    //            sibling = parent.left;
    //        }
    //
    //        // 如果兄弟节点是黑色。看兄弟节点是否有至少一个红色节点可以借
    //        if (isBlack(sibling.left) && isBlack(sibling.right)) {
    //            // 兄弟节点没有一个红色子节点，父节点要向下和兄弟节点合并 将父节点染为黑色，兄弟节点染为红色
    //            boolean parentBlack = isBlack(parent);
    //            black(parent);
    //            red(sibling);
    //            // 如果父节点是黑色，把父节点当成被删除的节点处理
    //            if (parentBlack) {
    //                afterRemove(parent, null);
    //            }
    //
    //        } else {
    //            // 兄弟节点至少有一个红色子节点，向兄弟节点借元素
    //            // 如果兄弟节点的左边是黑色，兄弟要先RR左旋转，
    //            //
    //            if (isBlack(sibling.left)){
    //                rotateLeft(sibling);
    //                //  左旋后，兄弟节点变为了父节点的左边
    //                sibling = parent.left;
    //            }
    //            // 将兄弟节点染为之前父节点的颜色
    //            // 兄弟节点的左边染为黑色
    //            // 父节点染为黑色
    //            color(sibling,colorOf(parent));
    //            black(sibling.left);
    //            black(parent);
    //            // 父节点LL右旋转
    //            rotateRight(parent);
    //        }
    //
    //    }
    //
    //
    //}

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
            if (color == RED) {
                sb.append("_red");
            }
            return sb.toString();
        }
    }
}