package com.raven.algorithm.structure.base_05_binarysearchtree;

import com.raven.algorithm.structure.utils.printer.BinaryTrees;
import org.apache.commons.lang3.RandomUtils;

import java.util.Random;

/**
 * Description:
 * date: 2022/6/23 21:22
 *
 * @author raven
 */
public class Main {

    public static void main(String[] args) {

        //compareInteger();
        //comparable();
        //comparator();
        //height();
        //isComplete();
        remove();
    }

    private static void remove() {
        Integer[] intArray = new Integer[]{
                7, 4, 9, 2, 5, 8, 11, 3, 1
        };

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (Integer num : intArray) {
            tree.add(num);
        }

        BinaryTrees.println(tree);
        tree.remove(4);
        System.out.println("===========");
        BinaryTrees.println(tree);
        tree.remove(2);
        System.out.println("===========");
        BinaryTrees.println(tree);
        tree.remove(7);
        System.out.println("===========");
        BinaryTrees.println(tree);
    }

    private static void isComplete() {
        Integer[] intArray = new Integer[]{
                7, 4, 9, 2, 5, 8, 11, 3, 1
        };

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (Integer num : intArray) {
            tree.add(num);
        }

        BinaryTrees.println(tree);
        System.out.println(tree.isComplete());
    }

    private static void compareInteger() {
        Integer[] intArray = new Integer[]{
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (Integer num : intArray) {
            tree.add(num);
        }

        BinaryTrees.println(tree);
        tree.preorder(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print("preorder_" + element + "_");
                return element == 2;
            }
        });
        System.out.println();
        tree.inorder(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print("inorder_" + element + "_");
                return element == 4;
            }
        });
        System.out.println();
        tree.postorder(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print("postorder_" + element + "_");
                return element == 2;
            }
        });
        System.out.println();
        tree.levelOrder(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print("levelOrder_" + element + "_");
                return element == 9;
            }
        });

        //tree.levelOrderTraversal();
        //tree.preOrderTraversal();
        //tree.inOrderTraversal();
        //tree.postOrderTraversal();
        //System.out.println(tree);
    }

    static void height() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i = 0; i < 15; i++) {
            tree.add(RandomUtils.nextInt(1, 100));
        }
        BinaryTrees.println(tree);
        System.out.println(tree.height());
    }

    static void comparable() {
        BinarySearchTreeExtendComparable<Order> orderBinarySearchTree = new BinarySearchTreeExtendComparable<>();
        orderBinarySearchTree.add(new Order(16));
        orderBinarySearchTree.add(new Order(11));
        orderBinarySearchTree.add(new Order(9));
        orderBinarySearchTree.add(new Order(12));
        orderBinarySearchTree.add(new Order(8));
        orderBinarySearchTree.add(new Order(17));
        orderBinarySearchTree.add(new Order(13));
        BinaryTrees.println(orderBinarySearchTree);
    }

    static void comparator() {
        BinarySearchTree<Order> orderBinarySearchTree = new BinarySearchTree<>((o1, o2) -> o2.getId() - o1.getId());
        BinarySearchTree<Order> orderIdCompareTree = new BinarySearchTree<>(new OrderIdComparator());
        BinarySearchTree<Order> orderTypeCompareTree = new BinarySearchTree<>(new OrderTypeComparator());

        orderBinarySearchTree.add(new Order(16, 0));
        orderBinarySearchTree.add(new Order(11, 0));
        orderBinarySearchTree.add(new Order(9, 0));
        orderBinarySearchTree.add(new Order(12, 0));
        orderBinarySearchTree.add(new Order(8, 0));
        orderBinarySearchTree.add(new Order(17, 0));
        orderBinarySearchTree.add(new Order(13, 0));
        BinaryTrees.println(orderBinarySearchTree);
        orderBinarySearchTree.add(new Order(13, 1));
        BinaryTrees.println(orderBinarySearchTree);


    }


}