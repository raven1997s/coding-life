package com.raven.data_structures_and_algorithms.structure.base_14_tree_traversal;

import com.raven.data_structures_and_algorithms.structure.utils.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Description:
 * date: 2022/7/5 21:14
 *
 * @author raven
 */
public class BinaryTree<E> implements BinaryTreeInfo {

    protected int size;
    // 根节点
    protected Node<E> root;

    protected static class Node<E> {
        E element;
        Node<E> parent;
        Node<E> left;
        Node<E> right;
        // 给节点添加height元素，记录节点的高度，但高度只有avl树需要，普通的二叉树并不需要，所以进行抽象

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
        public Node<E> sibling() {
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
            return "Node{" +
                    "element=" + element +
                    '}';
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

    ///**
    // * 前序遍历
    // * 先遍历root节点 再遍历左子节点 再遍历右子节点，遵循这一原则遍历完所有元素
    // */
    //public void preOrderTraversal() {
    //    preOrderTraversal(root);
    //}
    //
    //private void preOrderTraversal(Node<E> node) {
    //    if (node == null) {
    //        return;
    //    }
    //    System.out.println(node.element);
    //    preOrderTraversal(node.left);
    //    preOrderTraversal(node.right);
    //}
    //
    ///**
    // * 中序遍历
    // * 先遍历左子节点 再遍历parent(root)节点 最后遍历右子节点
    // * 二叉搜索树进行中序遍历会从小到大输出元素
    // */
    //public void inOrderTraversal() {
    //    inOrderTraversal(root);
    //}
    //
    //private void inOrderTraversal(Node<E> node) {
    //    if (node == null) {
    //        return;
    //    }
    //    inOrderTraversal(node.left);
    //    System.out.println(node.element);
    //    inOrderTraversal(node.right);
    //}
    //
    ///**
    // * 后序遍历
    // * 先遍历左子节点 再遍历右子节点，再遍历root节点 遵循这一原则遍历完所有元素
    // */
    //public void postOrderTraversal() {
    //    postOrderTraversal(root);
    //}
    //
    //private void postOrderTraversal(Node<E> node) {
    //    if (node == null) {
    //        return;
    //    }
    //    postOrderTraversal(node.left);
    //    postOrderTraversal(node.right);
    //    System.out.println(node.element);
    //}

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


    /**
     * 前序遍历
     * 先遍历节点 并把右子树放入栈中 然后一路向左遍历  当没有左子树时弹出栈顶 继续遍历 直到栈中元素全部遍历完毕
     *
     * @param visitor
     */
    public void preorder(Visitor<E> visitor) {
        if (root == null || visitor == null) {
            return;
        }
        Node<E> node = root;
        Stack<Node<E>> stack = new Stack<>();
        while (true) {
            if (node != null) {
                // 访问节点
                if (visitor.visit(node.element)) {
                    return;
                }
                // 右子树进栈
                if (node.right != null) {
                    stack.push(node.right);
                }
                // 向左走
                node = node.left;
            } else if (stack.isEmpty()) {
                // 栈为null 遍历完毕
                return;
            } else {
                // 节点没有左子树时访问栈顶元素
                node = stack.pop();
            }
        }
    }


    /**
     * 中序遍历
     * 一路向左并把节点入栈，当节点没有左子树时弹出栈顶元素访问，访问后再访问右子树节点，然后继续前面的遍历。直至栈里元素为空
     *
     * @param visitor
     */
    public void inorder(Visitor<E> visitor) {
        if (visitor == null || root == null) {
            return;
        }

        Node<E> node = root;
        Stack<Node<E>> stack = new Stack<>();
        while (true) {
            if (node != null) {
                // 左子树进栈
                stack.push(node);
                node = node.left;
            } else if (stack.isEmpty()) {
                return;
            } else {
                // 弹出栈顶元素访问
                node = stack.pop();
                if (visitor.visit(node.element)) {
                    return;
                }
                // 让右子树进行中序遍历
                node = node.right;
            }
        }
    }

    /**
     * 后序遍历
     * 从root节点逐个向下遍历 将途经的节点全部入栈，直到节点没有子节点或者节点是弹出节点的子节点就进行访问
     *
     * @param visitor
     */
    public void postorder(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }

        // 记录上一次弹出栈的元素
        Node<E> prev = null;
        Stack<Node<E>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            // 获取栈顶元素
            Node<E> top = stack.peek();
            // 如果栈顶元素是叶子节点 或者栈顶元素是上一次弹出元素的父节点，那么现在就该访问栈顶元素
            if (top.isLeaf() || (prev != null && top == prev.parent)) {
                // 弹出栈顶元素访问
                prev = stack.pop();
                if (visitor.visit(prev.element)) {
                    return;
                }
            } else {
                if (top.right != null) {
                    stack.push(top.right);
                }
                if (top.left != null) {
                    stack.push(top.left);
                }
            }
        }
    }

    //public void postorder(Visitor<E> visitor) {
    //    if (visitor == null) {
    //        return;
    //    }
    //    postorder(visitor, root);
    //}
    //
    //private void postorder(Visitor<E> visitor, Node<E> node) {
    //    // 减少收到终止命令后多余的递归调用
    //    if (node == null || visitor.stop) {
    //        return;
    //    }
    //
    //    postorder(visitor, node.left);
    //    postorder(visitor, node.right);
    //    // 收到终止条件 停止递归遍历 停止处理元素
    //    if (visitor.stop) {
    //        return;
    //    }
    //    // 指向完毕后返回是否停止终止的标识 并记录到抽象类中
    //    visitor.stop = visitor.visit(node.element);
    //}
    //
    //public void inorder(Visitor<E> visitor) {
    //    if (visitor == null) {
    //        return;
    //    }
    //    inorder(visitor, root);
    //}
    //
    //private void inorder(Visitor<E> visitor, Node<E> node) {
    //    // 如果节点为空或者收到终止条件 停止递归遍历
    //    if (node == null || visitor.stop) {
    //        return;
    //    }
    //
    //    inorder(visitor, node.left);
    //    // 收到终止条件 停止递归遍历 防止左子树已经返回停止继续处理元素
    //    if (visitor.stop) {
    //        return;
    //    }
    //    // 指向完毕后返回是否停止终止的标识 并记录到抽象类中
    //    visitor.stop = visitor.visit(node.element);
    //    inorder(visitor, node.right);
    //}
    //
    //public void preorder(Visitor<E> visitor) {
    //    if (visitor == null) {
    //        return;
    //    }
    //    preorder(visitor, root);
    //}
    //
    //private void preorder(Visitor<E> visitor, Node<E> node) {
    //    // 如果节点为空或者收到终止条件 停止递归遍历
    //    if (node == null || visitor.stop) {
    //        return;
    //    }
    //
    //    visitor.stop = visitor.visit(node.element);
    //    preorder(visitor, node.left);
    //    preorder(visitor, node.right);
    //}

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
     * 前驱节点：中序遍历时的前一个节点
     * 获取指定节点的前驱节点
     *
     * @param node
     * @return
     */
    protected Node<E> predecessor(Node<E> node) {
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
    protected Node<E> successor(Node<E> node) {
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
        return node;
    }

    protected void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

}