package com.raven.data_structures_and_algorithms.structure.base_10_map.linkedhashmap;

import com.raven.utils.printer.BinaryTreeInfo;
import com.raven.utils.printer.BinaryTrees;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * Description:
 * date: 2022/7/24 21:17
 * hash值扰动计算
 * 支持扩容
 *
 * @author raven
 */
public class HashMap<K, V> implements Map<K, V> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private static final int DEFAULT_CAPACITY = 1 << 4;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
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
        // 添加元素时判断是否需要扩容
        resize();
        int index = index(key);
        // 获取index位置的红黑树根节点
        Node<K, V> root = table[index];
        // 如果index位置元素位空，则该位置没有元素，直接插入
        if (root == null) {
            root = createNode(key, value, null);
            // 将红黑树放到该索引位置
            table[index] = root;
            size++;
            // 将根节点染黑
            fixAfterPut(root);
            return null;
        }

        // 添加新的节点到红黑树上
        // 添加的不是第一个节点
        Node<K, V> parent;
        Node<K, V> node = root;
        Node<K, V> result;
        // 是否已经在树上搜索过元素
        boolean searched = false;
        // 记录比较结果，确定元素添加到父节点的那个方向
        int cmp;
        K k1 = key;
        int h1 = hash(k1);
        // 当节点元素比较到叶子节点，叶子节点没有子节点，比较结束，叶子节点为节点元素节点的父节点
        do {
            // 用节点元素和树上的节点元素比较大小
            // 如果节点元素大于树上的节点元素，则继续和节点元素的右节点进行比较
            K k2 = node.key;
            int h2 = node.hash;
            if (h1 > h2) {
                cmp = 1;
            } else if (h1 < h2) {
                cmp = -1;
            } else if (Objects.equals(k1, k2)) {
                cmp = 0;
            } else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    // compareTo 代表俩个对象的大小相当，但不能认为他们对象相当
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0) {
                // hash值相等 equals不等 key具有可比较性
            } else if (searched) {// searched = true 已经搜索过，不需要继续搜索树上是否存在该key
                cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
            } else {
                // hash值相等 equals不等 key不具备可比性
                // 在左右子树上遍历查找key是否存在
                if ((node.right != null && (result = node(k1, node.right)) != null) ||
                        (node.left != null && (result = node(k1, node.left)) != null)) {
                    // 将找到的节点赋值给node 后面统一处理
                    node = result;
                    cmp = 0;
                } else {
                    searched = true;
                    cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
                }
            }
            // 比较前记录父节点 创建节点时使用
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                // 节点元素和树上元素相等 重新赋值
                V oldVlaue = node.value;
                node.key = key;
                node.value = value;
                return oldVlaue;
            }
        } while (node != null);

        // 根据不同的树，创建不同类型的节点，如BST创建Node，AVL创建AVLNode
        Node<K, V> newNode = createNode(key, value, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
        // 添加完节点后的操作，如果是AVL树需要判断是否需要旋转平衡节点
        fixAfterPut(newNode);
        return null;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = node(key);
        return node != null ? node.value : null;
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
        // 遍历桶数组上每一刻红黑树 看是否存在
        if (size == 0) {
            return false;
        }

        Queue<Node<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            Node<K, V> root = table[i];
            if (root == null) {
                continue;
            }
            queue.offer(root);
            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();
                if (Objects.equals(node.value, value)) {
                    return true;
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {

        if (size == 0 || visitor == null) {
            return;
        }

        Queue<Node<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            Node<K, V> root = table[i];
            if (root == null) {
                continue;
            }
            queue.offer(root);
            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();
                if (visitor.visit(node.key, node.value)) {
                    return;
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

    }

    public void print() {
        if (size == 0) {
            return;
        }
        for (int i = 0; i < table.length; i++) {
            Node<K, V> root = table[i];
            System.out.println("[index = " + i + "]");
            BinaryTrees.println(new BinaryTreeInfo() {
                @Override
                public Object root() {
                    return root;
                }

                @Override
                public Object left(Object node) {
                    return ((HashMap.Node<K, V>) node).left;
                }

                @Override
                public Object right(Object node) {
                    return ((HashMap.Node<K, V>) node).right;
                }

                @Override
                public Object string(Object node) {
                    return node;
                }
            });
        }
    }

    /**
     * 扩容
     */
    private void resize() {
        //当节点总数量 / 哈希表数组长度 > 负载因子，则需要扩容
        if ((size / table.length) <= DEFAULT_LOAD_FACTOR) {
            return;
        }

        // 构建新的哈希表桶数组
        Node<K, V>[] oldTable = table;
        table = new Node[oldTable.length << 1];

        // 遍历旧的桶数据，将节点逐个挪动到新的数组中
        Queue<Node<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < oldTable.length; i++) {
            Node<K, V> root = oldTable[i];
            if (root == null) {
                continue;
            }
            queue.offer(root);
            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
                // 挪动节点  放最后是因为挪动时是把节点当成新的节点插入到新的桶数组中，right left parent 等关系需要断掉
                moveNode(node);
            }
        }
    }

    private void moveNode(Node<K, V> newNode) {
        // 断掉节点和其他节点的关系
        newNode.parent = null;
        newNode.left = null;
        newNode.right = null;
        newNode.color = RED;

        int index = index(newNode.key);
        // 获取index位置的红黑树根节点
        Node<K, V> root = table[index];
        // 如果index位置元素位空，则该位置没有元素 将传进来的节点放到根节点的位置
        if (root == null) {
            root = newNode;
            // 将红黑树放到该索引位置
            table[index] = root;
            // 将根节点染黑
            fixAfterPut(root);
            return;
        }

        // 添加新的节点到红黑树上
        // 添加的不是第一个节点
        Node<K, V> parent;
        Node<K, V> node = root;
        Node<K, V> result;
        // 是否已经在树上搜索过元素
        boolean searched = false;
        // 记录比较结果，确定元素添加到父节点的那个方向
        int cmp;
        K k1 = newNode.key;
        int h1 = newNode.hash;
        // 当节点元素比较到叶子节点，叶子节点没有子节点，比较结束，叶子节点为节点元素节点的父节点
        do {
            // 用节点元素和树上的节点元素比较大小
            // 如果节点元素大于树上的节点元素，则继续和节点元素的右节点进行比较
            K k2 = node.key;
            int h2 = node.hash;
            if (h1 > h2) {
                cmp = 1;
            } else if (h1 < h2) {
                cmp = -1;
            } else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    // compareTo 代表俩个对象的大小相当，但不能认为他们对象相当
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0) {
                // hash值相等 equals不等 key具有可比较性
            } else {// searched = true 已经搜索过，不需要继续搜索树上是否存在该key
                cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
            }
            // 比较前记录父节点 创建节点时使用
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            }
        } while (node != null);

        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        // 重新设置节点的parent属性
        newNode.parent = parent;
        // 添加完节点后的操作，如果是AVL树需要判断是否需要旋转平衡节点
        fixAfterPut(newNode);
    }

    protected V remove(Node<K, V> node) {
        // 如果节点不存在，则不需要删除，直接返回
        if (node == null) {
            return null;
        }

        Node<K, V> willNode = node;
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
            node.hash = preNode.hash;
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
        int index = index(node);
        // 如果子节点不为空，要被删除的节点一定是度为一的节点  （如果node度为二，上面已经将值替换，要删除就是前驱节点或者后继节点，他们度一定为一）
        if (replacement != null) { //node是度为1的节点
            // 设置replacement的parent，
            replacement.parent = node.parent;
            // node是度为一的root节点
            if (node.parent == null) {
                // replacement.parent 设置为null的操作上面已经处理
                table[index] = replacement;
            } else if (node == node.parent.left) {
                // 如果node是父节点的左子树 则用 replacement取代node的parent的左子树
                node.parent.left = replacement;
            } else if (node == node.parent.right) {
                node.parent.right = replacement;
            }
            // 传入replacement 不会影响AVL树和红黑树的平衡处理
            fixAfterRemove(replacement);
        } else if (node.parent == null) { //node是度为0的节点，并且是root节点
            // 删除root节点
            table[index] = null;
            fixAfterRemove(node);
        } else { // node是度为0的节点，并且不是root节点
            // 如果node是node父节点的左子树，则将副节点的左子树设置为null
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
            fixAfterRemove(node);
        }
        afterRemove(willNode, node);
        return oldValue;
    }


    /**
     * 删除节点后的行为
     *
     * @param willNode   期望被删除的节点
     * @param removeNode 真正被删除的节点
     */
    protected void afterRemove(Node<K, V> willNode, Node<K, V> removeNode) {

    }

    private Node<K, V> node(K key) {
        // 找到key在哈希表桶数组的索引。通过索引找到root节点 从根节点开始遍历查找整个红黑树
        Node<K, V> root = table[index(key)];
        return node(key, root);
    }

    private Node<K, V> node(K k1, Node<K, V> node) {
        // 找到key在哈希表桶数组的索引。通过索引找到root节点
        // hashcode 扰动计算
        int h1 = hash(k1);
        // 用来存储从红黑树左右子树中查出来的结果数据
        Node<K, V> result;
        int cmp;
        while (node != null) {
            K k2 = node.key;
            int h2 = node.hash;
            // 直接比较hash值大小，防止做差比较溢出
            if (h1 > h2) {
                node = node.right;
            } else if (h1 < h2) {
                node = node.left;
            } else if (Objects.equals(k1, k2)) {
                // hash值相等，equals比较是否是同一对象
                return node;
            } else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    // compareTo相当不能认为是同一对象 equals比较是否是同一对象
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0) {
                // hash值相等 equals不等 具有可比较性通过Comparable进行比较
                node = cmp > 0 ? node.right : node.left;
            } else if (node.right != null && ((result = node(k1, node.right)) != null)) {
                // hash值相等 equals不等，不具备可比较性 递归从红黑树的右边查找比较 如果找到则直接返回
                return result;
            } else {
                node = node.left;
            }
            //} else if (node.left != null && ((result = node(k1, node.left)) != null)) {
            //    return result;
            //} else {
            //    return null;
            //}
        }
        // 遍历结束未找到节点。证明key不存在
        return null;
    }

    //
    ///**
    // * 比较key的大小
    // *
    // * @param k1
    // * @param k2
    // * @param h1 key1的hashCode
    // * @param h2 key2的hashCode
    // * @return
    // */
    //private int compare(K k1, K k2, int h1, int h2) {
    //    // 比较hashCode 如果hashCode不相等直接返回,继续和其他节点比较大小
    //    int result = h1 - h2;
    //    if (result != 0) {
    //        return result;
    //    }
    //
    //    // h1 = h2 hashCode相等 比较equals
    //    if (Objects.equals(k1, k2)) {
    //        // 如果equals相等，则证明俩个对象相等
    //        return 0;
    //    }
    //
    //    // hashCode相等 ， equals不等
    //    if (k1 != null && k2 != null) {
    //        // 根据类名长度进行比较
    //        String key1Cls = k1.getClass().getName();
    //        String key2Cls = k2.getClass().getName();
    //        result = key1Cls.compareTo(key2Cls);
    //        if (result != 0) {
    //            return result;
    //        }
    //
    //        // 如果是同一种类型并且具有可比较性 通过比较器进行比较
    //        if (k1 instanceof Comparable) {
    //            return ((Comparable) k1).compareTo(k2);
    //        }
    //    }
    //
    //    // 同一种类型 哈希值相等，但是不具备可比较性
    //    // key1为null && key2不为空
    //    // key1不为空 && key2为空
    //    // 通过内存地址对应的hashCode比较
    //    return System.identityHashCode(k1) - System.identityHashCode(k2);
    //}

    private void fixAfterPut(Node<K, V> node) {
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
            fixAfterPut(grand);
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

    private void fixAfterRemove(Node<K, V> node) {
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
                    fixAfterRemove(parent);
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
                    fixAfterRemove(parent);
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

    /**
     * 根据key生成对应的索引(在桶数组中的位置)
     *
     * @param key
     * @return
     */
    private int index(K key) {
        // 获取hashCode的高16位 ，让高16位与低16位进行异或运算，确保生成的索引在数组中位置更均匀
        // 使用最后的hash值与数组的长度-1进行与运算，获取索引位置(类似% 但是更高效)
        return hash(key) & (table.length - 1);
    }

    /**
     * 扰动计算hashCode
     *
     * @param key
     * @return
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        // 获取key的hashCode
        int hash = key.hashCode();
        // 获取hashCode的高16位 ，让高16位与低16位进行异或运算，确保生成的索引在数组中位置更均匀
        // 使用最后的hash值与数组的长度-1进行与运算，获取索引位置(类似% 但是更高效)
        return hash ^ (hash >>> 16);
    }

    private int index(Node<K, V> node) {
        // 内部已经使用高32位与低32位扰动计算 这里不需要实时位运算了
        return node.hash & (table.length - 1);
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
            // 获取root节点在哈希表中的索引位置 (root grand parent child 都在同一颗红黑树上)
            table[index(grand)] = parent;
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
    protected Node<K, V> createNode(K key, V value, Node<K, V> parent) {
        return new Node<>(key, value, parent);
    }

    protected static class Node<K, V> {
        private boolean color = RED;
        int hash;
        K key;
        V value;
        Node<K, V> parent;
        Node<K, V> left;
        Node<K, V> right;

        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            // 因为key是不变的 hashCode()计算逻辑也是不变的，所以可以提前算好hash值，不需要每次都实时位运算
            int hash = key == null ? 0 : key.hashCode();
            this.hash = (hash ^ (hash >>> 16));
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
            StringBuilder sb = new StringBuilder("key_" + key + "_value_" + value);
            if (color == RED) {
                sb.append("_red");
            }
            return sb.toString();
        }
    }
    // ================================================节点内部相关end========================================================

}