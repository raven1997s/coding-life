package com.raven.data_structures_and_algorithms.structure.base_10_map.hashmap.map;


import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * Description:
 * date: 2022/7/21 09:58
 *
 * @author raven
 */
public class TreeMap<K, V> implements Map<K, V> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    private int size;
    // 根节点
    private Node<K, V> root;

    // 自定义比较器
    private final Comparator<K> comparator;

    /**
     * 使用默认比较器
     */
    public TreeMap() {
        this(null);
    }

    /**
     * 使用自定义比较器
     *
     * @param comparator
     */
    public TreeMap(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    /**
     * key 相同时返回之前的value值
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public V put(K key, V value) {
        // 树上的元素不能为null
        keyNotNullCheck(key);

        // 添加第一个节点
        if (root == null) {
            root = createNode(key, value, null);
            size++;
            afterPut(root);
            return null;
        }

        // 添加的不是第一个节点
        Node<K, V> node = root;
        Node<K, V> parent = root;
        // 记录比较结果，确定元素添加到父节点的那个方向
        int result = 0;
        // 当节点元素比较到叶子节点，叶子节点没有子节点，比较结束，叶子节点为节点元素节点的父节点
        while (node != null) {
            // 用节点元素和树上的节点元素比较大小
            // 如果节点元素大于树上的节点元素，则继续和节点元素的右节点进行比较
            result = compare(key, node.key);
            // 比较前记录父节点 创建节点时使用
            parent = node;
            if (result > 0) {
                node = node.right;
            } else if (result < 0) {
                node = node.left;
            } else {
                // 节点元素和树上元素相等 重新赋值
                V oldVlaue = node.value;
                node.key = key;
                node.value = value;
                return oldVlaue;
            }
        }

        // 根据不同的树，创建不同类型的节点，如BST创建Node，AVL创建AVLNode
        Node<K, V> newNode = createNode(key, value, parent);
        if (result > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
        // 添加完节点后的操作，如果是AVL树需要判断是否需要旋转平衡节点
        afterPut(newNode);
        return null;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = node(key);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    @Override
    public V remove(K key) {
        return remove(node(key));
    }

    @Override
    public boolean containsKey(K key) {
        return node(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        if (root == null) {
            return false;
        }

        Queue<Node<K, V>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<K, V> node = queue.poll();
            if (valEquals(node.value, value)) {
                return true;
            }

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (visitor == null) {
            return;
        }
        traversal(visitor, root);
    }

    private boolean valEquals(V v1, V v2) {
        return Objects.equals(v1, v2);
    }

    private void afterPut(Node<K, V> node) {
        Node<K, V> parent = node.parent;
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
        Node<K, V> uncle = parent.sibling();
        Node<K, V> grand = red(parent.parent);
        // 叔父节点是红色，添加节点后需要进行上溢操作 (类比四阶B树此时超级节点已经有三个元素，所以需要上溢处理，父节点，祖父节点，叔父节点)
        if (isRed(uncle)) {
            // 将父节点和叔父节点染为黑色
            black(parent);
            black(uncle);
            // 祖父节点向上溢出(染为红色，当成新的节点加入处理)（上面已经统一处理）
            afterPut(grand);
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

    private void afterRemove(Node<K, V> node) {
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

        Node<K, V> parent = node.parent;
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
        Node<K, V> sibling = left ? parent.right : parent.left;
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
                if (isBlack(sibling.right)) {
                    rotateRight(sibling);
                    //  右旋后，兄弟节点变为了父节点的右边
                    sibling = parent.right;
                }
                // 将兄弟节点染为之前父节点的颜色
                // 兄弟节点的右边染为黑色
                // 父节点染为黑色
                color(sibling, colorOf(parent));
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
                if (isBlack(sibling.left)) {
                    rotateLeft(sibling);
                    //  左旋后，兄弟节点变为了父节点的左边
                    sibling = parent.left;
                }
                // 将兄弟节点染为之前父节点的颜色
                // 兄弟节点的左边染为黑色
                // 父节点染为黑色
                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                // 父节点LL右旋转
                rotateRight(parent);
            }
        }
    }

    private V remove(Node<K, V> node) {
        // 如果节点不存在，则不需要删除，直接返回
        if (node == null) {
            return null;
        }

        // 树的元素个数减一
        size--;
        V oldValue = node.value;
        // 如果被删除的节点度为二，删除节点需要 则找到节点的前驱节点 或者后继节点 ，将值替换到节点中。然后删除前驱(后继节点)，在保持树结构不变的情况下，删除元素
        if (node.hasTwoChildren()) {
            Node<K, V> preNode = predecessor(node);
            // 因为节点的度为2，所以节点的前驱节点or后继节点一定不为null
            // 将前驱节点的值替换到节点中
            node.key = preNode.key;
            node.value = preNode.value;
            // 删除前驱节点 将前驱节点赋值给node，复用删除度为一的节点的代码
            node = preNode;
        }

        /**
         * 删除度为一或者度为0的节点
         * 如果被删除的节点度为一，需要用要被删除节点的子节点替换被删除的节点
         * 需要判断要被删除的节点是父节点的左子树还是右子树，如果要被删除节点是父节点的左子树，则需要让父节点的left指向要被删除的子节点，并且维护子节点的parent为要被删除节点的parent
         * 需要判断子节点是node的左子树还是右子树，如果是左子树，则用左节点来取代他，如果是右子树，则用右节点来取代他
         */
        // 判断由哪个子节点来取代要被删除的node节点（node的度必然是1后者0）
        Node<K, V> replacement = node.left != null ? node.left : node.right;
        // 如果子节点不为空，要被删除的节点一定是度为一的节点  （如果node度为二，上面已经将值替换，要删除就是前驱节点或者后继节点，他们度一定为一）
        if (replacement != null) { //node是度为1的节点
            // 设置replacement的parent，
            replacement.parent = node.parent;
            // node是度为一的root节点
            if (node.parent == null) {
                // replacement.parent 设置为null的操作上面已经处理
                root = replacement;
            } else if (node == node.parent.left) {
                // 如果node是父节点的左子树 则用 replacement取代node的parent的左子树
                node.parent.left = replacement;
            } else if (node == node.parent.right) {
                node.parent.right = replacement;
            }
            // 传入replacement 不会影响AVL树和红黑树的平衡处理
            afterRemove(replacement);
        } else if (node.parent == null) { //node是度为0的节点，并且是root节点
            // 删除root节点
            root = null;
            afterRemove(node);
        } else { // node是度为0的节点，并且不是root节点
            // 如果node是node父节点的左子树，则将副节点的左子树设置为null
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
            afterRemove(node);
        }
        return oldValue;
    }

    /**
     * 根据节点元素获取节点
     *
     * @param key 元素
     * @return 返回元素对应的节点
     */
    private Node<K, V> node(K key) {
        Node<K, V> node = root;
        // 从根节点开始查找节点对应的元素
        while (node != null) {
            // 如果节点元素和传入的元素相同 则返回节点
            int cmr = compare(node.key, key);
            if (cmr == 0) {
                return node;
            } else if (cmr > 0) {
                // 节点元素 大于 传入的元素 则从节点的左子树中寻找元素对应的节点
                node = node.left;
            } else {
                // 节点元素 小 传入的元素 则从节点的右子树中寻找元素对应的节点
                node = node.right;
            }
        }
        // 查找完整棵树，都未找到传入元素对应的节点，则返回null
        return null;
    }

    /**
     * 对象比较方式：
     * planA：写死比较方式 pass 比较的纬度和方式时不确定的，不能使用简单的固定的数值类型的的比较
     * planB：定义接口comparable  BinarySearchTree继承接口 所有的树创建时指定比较方式  pass  同一对象不能右多种 比较的方式
     * planC: 定义接口comparator   BST 实现接口 compartor 创建树对象时，比较器由外部提供
     * 1 : e1 > e2  0 : e1 = e2  -1 : e1 < e2
     *
     * @param key1
     * @param key2
     * @return // 1 : e1 > e2  0 : e1 = e2  -1 : e1 < e2
     */
    private int compare(K key1, K key2) {
        if (comparator != null) {
            return comparator.compare(key1, key2);
        }
        // 如果没有传比较器 则使用默认比较器 即创建树时 对象必须实现(Comparable<E>)接口 指定比较器
        return ((Comparable<K>) key1).compareTo(key2);
    }

    private void keyNotNullCheck(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
    }

// ================================================节点遍历相关start========================================================
    private void traversal(Visitor<K, V> visitor, Node<K, V> node) {
        // 减少收到终止命令后多余的递归调用
        if (node == null || visitor.stop) {
            return;
        }

        traversal(visitor, node.left);
        // 收到终止条件 停止递归遍历 停止处理元素
        if (visitor.stop) {
            return;
        }
        // 指向完毕后返回是否停止终止的标识 并记录到抽象类中
        visitor.stop = visitor.visit(node.key, node.value);
        traversal(visitor, node.right);
    }

    /**
     * 前驱节点：中序遍历时的前一个节点
     * 获取指定节点的前驱节点
     *
     * @param node
     * @return
     */
    private Node<K, V> predecessor(Node<K, V> node) {
        // 如果节点是空的，则没有前驱节点
        if (node == null) {
            return null;
        }

        // 先从左子树中寻找前驱节点
        Node<K, V> p = node.left;
        // 如果节点的左子树不为null ，则通过node.left.right.right.right 寻找前驱节点，直到right为null
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            // 找到前驱节点
            return p;
        }

        // 如果节点的左子树为null的，则通过node.parent.parent.parent 寻找前驱节点，直到节点是parent的右子树
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }

        // 循环终止后，存在俩种可能
        // node.parent == null  node节点的父节点(祖父节点)为空，证明节点没有前驱节点，返回null 或node.parent都可以
        // node ==  node.parent.right node节点是node.parent的右子树，所以node的前驱节点为node.right(通过循环赋值后，此时的node.right可能是node的祖父辈节点)
        return node.parent;
    }

    /**
     * 后继节点：中序遍历的后一个节点
     *
     * @param node
     * @return
     */
    private Node<K, V> successor(Node<K, V> node) {
        if (node == null) {
            return null;
        }

        // 如果节点的右子树不为空，后继节点就在node的右子树中，则通过node.right.left.left.left寻找节点的后继节点，直到节点的left为null
        Node<K, V> s = node.right;
        if (s != null) {
            // 寻找node节点右子树中最左的节点
            while (s.left != null) {
                s = s.left;
            }
            // 找到后继节点
            return s;
        }

        // 如果节点右子树为null，则从节点的父节点中寻找节点的后继节点。node.parent.parent.parent 直到节点是parent的左子树。
        // 节点的父节点不为空并且节点是父节点的右子树，继续往上，直到节点是父节点的左子树或者直到找到节点的父节点为null
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }

        // node.parent == null 找到节点的父节点也未找到后继节点
        // node == node.parent.left 节点为父节点的左子树，后继节点为node.parent
        return node.parent;
    }

// ================================================节点遍历相关end========================================================

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