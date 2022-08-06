package com.raven.data_structures_and_algorithms.structure.base_09_listset.tree;

import java.util.Comparator;

/**
 * Description:
 * date: 2022/6/23 20:29
 * 实现 BinaryTreeInfo 便于打印树
 * 构造自定义比较器，可使用对象默认的比较器 ，也可再构建树的时候使用自定义的比较器
 * 定义遍历的接口，交与外界遍历时自定义实现 （抽象类的方式实现）
 * 判断二叉树是否时完全二叉树
 * 层序遍历计算高度
 * 获取节点的前驱节点
 * 获取节点的后继节点
 * 删除指定节点
 *
 * @author raven
 */
@SuppressWarnings("unchecked")
public class BST<E> extends BinaryTree<E> {

    // 自定义比较器
    private final Comparator<E> comparator;

    /**
     * 使用默认比较器
     */
    public BST() {
        this(null);
    }

    /**
     * 使用自定义比较器
     *
     * @param comparator
     */
    public BST(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void add(E element) {
        // 树上的元素不能为null
        elementNotNullCheck(element);

        // 添加第一个节点
        if (root == null) {
            // 根据不同的树，创建不同类型的节点，如BST创建Node，AVL创建AVLNode
            root = createNode(element, null);
            size++;
            // 添加完元素后，如果是AVL树判断是否需要旋转平衡节点
            afterAdd(root);
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

        // 根据不同的树，创建不同类型的节点，如BST创建Node，AVL创建AVLNode
        Node<E> newNode = createNode(element, parent);
        if (result > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
        // 添加完节点后的操作，如果是AVL树需要判断是否需要旋转平衡节点
        afterAdd(newNode);
    }

    /**
     * 添加完元素后的操作,如果是AVL树需要判断是否需要旋转平衡节点
     * 方法抽象，因为二叉搜索树是不需要进行调整平衡节点的
     */
    protected void afterAdd(Node<E> node) {
    }

    /**
     * 删除元素后可能会导致树失去平衡，如果是AVL树or红黑树则要平衡节点
     * 方法抽象。
     *
     * @param node 被删除的节点 或者用于替换被删除节点的子节点
     */
    protected void afterRemove(Node<E> node) {
    }

    /**
     * 提供方法模板，创建节点时通过模板创建，子类可根据需要自定义创建需要的节点
     *
     * @param element
     * @param parent
     * @return
     */
    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<>(element, parent);
    }

    public void remove(E e) {
        remove(node(e));
    }

    /**
     * 根据节点元素获取节点
     *
     * @param e 元素
     * @return 返回元素对应的节点
     */
    private Node<E> node(E e) {
        Node<E> node = root;
        // 从根节点开始查找节点对应的元素
        while (node != null) {
            // 如果节点元素和传入的元素相同 则返回节点
            int cmr = compare(node.element, e);
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

    private void remove(Node<E> node) {
        // 如果节点不存在，则不需要删除，直接返回
        if (node == null) {
            return;
        }

        // 树的元素个数减一
        size--;
        // 如果被删除的节点度为二，删除节点需要 则找到节点的前驱节点 或者后继节点 ，将值替换到节点中。然后删除前驱(后继节点)，在保持树结构不变的情况下，删除元素
        if (node.hasTwoChildren()) {
            Node<E> preNode = predecessor(node);
            // 因为节点的度为2，所以节点的前驱节点or后继节点一定不为null
            // 将前驱节点的值替换到节点中
            node.element = preNode.element;
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
        Node<E> replacement = node.left != null ? node.left : node.right;
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
    }

    public boolean contains(E e) {
        return node(e) != null;
    }

    /**
     * 对象比较方式：
     * planA：写死比较方式 pass 比较的纬度和方式时不确定的，不能使用简单的固定的数值类型的的比较
     * planB：定义接口comparable  BinarySearchTree继承接口 所有的树创建时指定比较方式  pass  同一对象不能右多种 比较的方式
     * planC: 定义接口comparator   BST 实现接口 compartor 创建树对象时，比较器由外部提供
     * 1 : e1 > e2  0 : e1 = e2  -1 : e1 < e2
     *
     * @param e1
     * @param e2
     * @return // 1 : e1 > e2  0 : e1 = e2  -1 : e1 < e2
     */
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        // 如果没有传比较器 则使用默认比较器 即创建树时 对象必须实现(Comparable<E>)接口 指定比较器
        return ((Comparable<E>) e1).compareTo(e2);
    }

}