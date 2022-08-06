package com.raven.data_structures_and_algorithms.structure.base_14_tree_traversal;

import com.raven.utils.Asserts;
import com.raven.utils.printer.BinaryTrees;

/**
 * Description:
 * date: 2022/8/6 18:39
 *
 * @author raven
 */
public class Main {
    public static void main(String[] args) {
        // 创建BST
        Integer[] data = new Integer[] {
                7, 4, 9, 2, 5, 8, 11
        };
        BST<Integer> bst = new BST<>();
        for (Integer datum : data) {
            bst.add(datum);
        }
        // 树状打印
        BinaryTrees.println(bst);
        // 遍历器
        StringBuilder sb = new StringBuilder();
        BinaryTree.Visitor<Integer> visitor = new BinaryTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                sb.append(element).append(" ");
                return false;
            }
        };
        // 遍历
        sb.delete(0, sb.length());
        bst.preorder(visitor);
        Asserts.test("7 4 2 5 9 8 11 ".equals(sb.toString()));

        sb.delete(0, sb.length());
        bst.inorder(visitor);
        Asserts.test("2 4 5 7 8 9 11 ".equals(sb.toString()));
        //
        sb.delete(0, sb.length());
        bst.postorder(visitor);
        Asserts.test("2 5 4 8 11 9 7 ".equals(sb.toString()));
    }
}
