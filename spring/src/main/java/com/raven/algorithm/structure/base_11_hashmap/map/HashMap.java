package com.raven.algorithm.structure.base_11_hashmap.map;

/**
 * Description:
 * date: 2022/7/24 21:17
 *
 * @author raven
 */
public class HashMap<K, V> implements Map<K, V> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private static final int DEFAULT_CAPACITY = 1 << 4;
    private int size;
    // 哈希表 每个元素都是一颗红黑树的root节点，
    private Node<K, V>[] table;

    public HashMap() {
        table = new Node[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        return null;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {

    }


// ================================================节点染色相关start========================================================

    /**
     * 染色接口 将node的颜色修改为指定颜色
     *
     * @param node
     * @param color
     * @return
     */
    private Node<K, V> color(Node<K, V> node, boolean color) {
        if (node == null) {
            return node;
        }
        node.color = color;
        return node;
    }

    /**
     * 将节点的颜色修改为红色
     *
     * @param node
     * @return
     */
    private Node<K, V> red(Node<K, V> node) {
        return color(node, RED);
    }

    /**
     * 将节点的颜色修改为黑色
     *
     * @param node
     * @return
     */
    private Node<K, V> black(Node<K, V> node) {
        return color(node, BLACK);
    }

    /**
     * 判断节点颜色是不是黑色
     *
     * @param node
     * @return
     */
    private boolean isBlack(Node<K, V> node) {
        return colorOf(node) == BLACK;
    }

    /**
     * 判断节点颜色是不是红色
     *
     * @param node
     * @return
     */
    private boolean isRed(Node<K, V> node) {
        return colorOf(node) == RED;
    }

    /**
     * 返回节点的颜色
     *
     * @param node
     * @return
     */
    private boolean colorOf(Node<K, V> node) {
        return node == null ? BLACK : node.color;
    }
    // ================================================节点染色相关end========================================================

    // ================================================节点旋转相关start========================================================

    /**
     * 左旋
     *
     * @param grand
     */
    private void rotateLeft(Node<K, V> grand) {
        // RR
        // 因为是左旋，所以parent一定是grand的right子树
        Node<K, V> parent = grand.right;
        // 获取要旋转位置的parent的左子树
        Node<K, V> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }

    /**
     * 右旋
     *
     * @param grand
     */
    private void rotateRight(Node<K, V> grand) {
        // LL
        // 右旋，parent一定是grand的左子树
        Node<K, V> parent = grand.left;
        // 获取要进行旋转的parent的right
        Node<K, V> child = parent.right;
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
    private void afterRotate(Node<K, V> grand, Node<K, V> parent, Node<K, V> child) {
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
    // ================================================节点旋转相关end========================================================


    // ================================================节点内部相关start========================================================
    private Node<K, V> createNode(K key, V value, Node<K, V> parent) {
        return new Node<>(key, value, parent);
    }

    private static class Node<K, V> {
        private boolean color = RED;

        K key;
        V value;
        Node<K, V> parent;
        Node<K, V> left;
        Node<K, V> right;

        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        /**
         * 左子节点 和右子节点都为空的时候为叶子节点
         *
         * @return
         */
        public boolean isLeaf() {
            return left == null && right == null;
        }

        /**
         * 左子节点 和右子节点都不为空的时候为叶子节点
         *
         * @return
         */
        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        /**
         * 返回当前节点是否为父节点的左子树
         *
         * @return
         */
        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        /**
         * 返回当前节点是否为父节点的右子树
         *
         * @return
         */
        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        /**
         * 返回当前节点的兄弟节点
         *
         * @return
         */
        public Node<K, V> sibling() {
            if (isRightChild()) {
                return parent.left;
            }
            if (isLeftChild()) {
                return parent.right;
            }
            return null;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(key + String.valueOf(value));
            if (color == RED) {
                sb.append("_red");
            }
            return sb.toString();
        }
    }
    // ================================================节点内部相关end========================================================

}