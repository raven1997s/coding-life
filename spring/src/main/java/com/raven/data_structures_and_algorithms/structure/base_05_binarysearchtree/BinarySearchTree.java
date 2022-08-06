package com.raven.data_structures_and_algorithms.structure.base_05_binarysearchtree;

import com.raven.data_structures_and_algorithms.structure.utils.printer.BinaryTreeInfo;

import java.util.*;

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
public class BinarySearchTree<E> implements BinaryTreeInfo {

    private int size;
    // 根节点
    private Node<E> root;

    // 自定义比较器
    private final Comparator<E> comparator;

    /**
     * 使用默认比较器
     */
    public BinarySearchTree() {
        this(null);
    }

    /**
     * 使用自定义比较器
     *
     * @param comparator
     */
    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    private static class Node<E> {
        E element;
        Node<E> parent;
        Node<E> left;
        Node<E> right;

        public Node(E element, Node<E> parent) {
            this.element = element;
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
    }

    public int size() {
        return size;
    }

    public void clear() {
        size = 0;
        root = null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E element) {
        // 树上的元素不能为null
        elementNotNullCheck(element);

        // 添加第一个节点
        if (root == null) {
            root = new Node<>(element, null);
            size++;
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

        if (result > 0) {
            parent.right = new Node<>(element, parent);
        } else {
            parent.left = new Node<>(element, parent);
        }
        size++;
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
        } else if (node.parent == null) { //node是度为0的节点，并且是root节点
            // 删除root节点
            root = null;
        } else { // node是度为0的节点，并且不是root节点
            // 如果node是node父节点的左子树，则将副节点的左子树设置为null
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        }

    }

    public boolean contains(E e) {
        return node(e) != null;
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> myNode = (Node<E>) node;
        String str;
        if (myNode.parent == null) {
            str = "_p(null)";
        } else {
            str = "_p(" + myNode.parent.element + ")";
        }
        return myNode.element + str;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, sb, "");
        return sb.toString();
    }

    private void toString(Node<E> node, StringBuilder sb, String prefix) {
        // 中序遍历打印数据
        if (node == null) {
            return;
        }
        toString(node.left, sb, prefix + "L--");
        // 拼接当前节点
        sb.append(prefix).append(node.element).append(System.lineSeparator());
        toString(node.right, sb, prefix + "R--");
    }

    /**
     * 判断二叉树是否时完全二叉树(利用队列进行层序遍历)
     * 完全二叉树: 叶子结点只能出现在最下层和次下层，且最下层的叶子结点集中在树的左部
     *
     * @return
     */
    public boolean isComplete() {
        // 如果根节点为空，认为不是完全二叉树
        if (root == null) {
            return false;
        }

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        boolean leafFlag = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (leafFlag && !node.isLeaf()) {
                return false;
            }

            if (node.left != null) {
                queue.offer(node.left);
            } else {
                // node.left == null && node.right != null
                // 完全二叉树的节点必须靠左对齐，所以如果节点拥有右子节点但没有左子节点时，则一定不是完全二叉树
                if (node.right != null) { //
                    return false;
                }
            }

            if (node.right != null) {
                queue.offer(node.right);
            } else {
                // 剩下俩种可能 node.right == null && (node.left == null  || node.left != null )
                // 接下来的节点一定是叶子节点。
                leafFlag = true;
            }
        }
        return true;
    }

    /**
     * 层序遍历计算高度 有多少层高度就是多少
     *
     * @return
     */
    public int height() {
        if (root == null) {
            return 0;
        }
        // 树的高度
        int height = 0;
        // 记录每一层的元素个数
        int levelSize = 1;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            // 取出元素时，该层剩余元素减一
            levelSize--;
            Node<E> left = node.left;
            if (left != null) {
                queue.offer(left);
            }
            Node<E> right = node.right;
            if (right != null) {
                queue.offer(right);
            }
            // 该层剩余元素为0时，这一层元素已经走完
            if (levelSize == 0) {
                // 此时队列中的元素个数就是下一层元素的个数
                levelSize = queue.size();
                // 一层结束 层数加一
                height++;
            }
        }
        return height;
    }


    /**
     * 树的高度就是根节点度高度
     * 递归计算树的高度
     *
     * @return
     */
    public int heightByRecursion() {
        return height(root);
    }

    /**
     * 计算指定节点度高度（递归计算）
     * 每一次度高度都等于他下一次层节点度高度+1
     *
     * @param node
     * @return
     */
    private int height(Node<E> node) {
        // 节点为空时，证明他到达根节点(left or right)
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * 前序遍历
     * 先遍历root节点 再遍历左子节点 再遍历右子节点，遵循这一原则遍历完所有元素
     */
    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    private void preOrderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        System.out.println(node.element);
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    /**
     * 中序遍历
     * 先遍历左子节点 再遍历parent(root)节点 最后遍历右子节点
     * 二叉搜索树进行中序遍历会从小到大输出元素
     */
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.left);
        System.out.println(node.element);
        inOrderTraversal(node.right);
    }

    /**
     * 后序遍历
     * 先遍历左子节点 再遍历右子节点，再遍历root节点 遵循这一原则遍历完所有元素
     */
    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    private void postOrderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.println(node.element);
    }

    /**
     * 层序遍历
     * 从根节点开始，一层一层从左到右挨个遍历元素
     */
    public void levelOrderTraversal() {
        if (root == null) {
            return;
        }
        // 层序遍历 利用队列存储元素顺序，将元素逐层挨个放入队列，后逐个取出。
        Queue<Node<E>> queue = new LinkedList<>();
        // 将根节点入队
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 取出节点输出
            Node<E> top = queue.poll();
            System.out.println(top.element);
            // 存储下一层元素 不为空才需要存储
            Node<E> left = top.left;
            if (left != null) {
                queue.add(left);
            }
            Node<E> right = top.right;
            if (right != null) {
                queue.add(right);
            }
        }
    }


    public void postorder(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        postorder(visitor, root);
    }

    private void postorder(Visitor<E> visitor, Node<E> node) {
        // 减少收到终止命令后多余的递归调用
        if (node == null || visitor.stop) {
            return;
        }

        postorder(visitor, node.left);
        postorder(visitor, node.right);
        // 收到终止条件 停止递归遍历 停止处理元素
        if (visitor.stop) {
            return;
        }
        // 指向完毕后返回是否停止终止的标识 并记录到抽象类中
        visitor.stop = visitor.visit(node.element);
    }


    public void inorder(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        inorder(visitor, root);
    }

    private void inorder(Visitor<E> visitor, Node<E> node) {
        // 如果节点为空或者收到终止条件 停止递归遍历
        if (node == null || visitor.stop) {
            return;
        }

        inorder(visitor, node.left);
        // 收到终止条件 停止递归遍历 防止左子树已经返回停止继续处理元素
        if (visitor.stop) {
            return;
        }
        // 指向完毕后返回是否停止终止的标识 并记录到抽象类中
        visitor.stop = visitor.visit(node.element);
        inorder(visitor, node.right);
    }

    public void preorder(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        preorder(visitor, root);
    }

    private void preorder(Visitor<E> visitor, Node<E> node) {
        // 如果节点为空或者收到终止条件 停止递归遍历
        if (node == null || visitor.stop) {
            return;
        }

        visitor.stop = visitor.visit(node.element);
        preorder(visitor, node.left);
        preorder(visitor, node.right);
    }

    /**
     * 层序遍历
     *
     * @param visitor 遍历的行为接口，外界自定义处理逻辑
     */
    public void levelOrder(Visitor<E> visitor) {
        if (root == null || visitor == null) {
            return;
        }

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (visitor.stop) {
                return;
            }
            visitor.stop = visitor.visit(node.element);
            Node<E> left = node.left;
            if (left != null) {
                queue.offer(left);
            }
            Node<E> right = node.right;
            if (right != null) {
                queue.offer(right);
            }
        }
    }

    /**
     * 处理遍历元素抽象类
     *
     * @param <E>
     */
    public abstract static class Visitor<E> {

        public boolean stop;

        /**
         * 遍历时处理元素的函数
         *
         * @param element 元素
         */
        public abstract boolean visit(E element);
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

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    /**
     * 前驱节点：中序遍历时的前一个节点
     * 获取指定节点的前驱节点
     *
     * @param node
     * @return
     */
    private Node<E> predecessor(Node<E> node) {
        // 如果节点是空的，则没有前驱节点
        if (node == null) {
            return null;
        }

        // 先从左子树中寻找前驱节点
        Node<E> p = node.left;
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
    private Node<E> successor(Node<E> node) {
        if (node == null) {
            return null;
        }

        // 如果节点的右子树不为空，后继节点就在node的右子树中，则通过node.right.left.left.left寻找节点的后继节点，直到节点的left为null
        Node<E> s = node.right;
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
}