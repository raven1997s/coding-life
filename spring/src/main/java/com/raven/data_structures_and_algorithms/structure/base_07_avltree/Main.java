package com.raven.data_structures_and_algorithms.structure.base_07_avltree;

import com.raven.data_structures_and_algorithms.structure.base_08_rbtree.RBTree;
import com.raven.utils.printer.BinaryTrees;

/**
 * Description:
 * date: 2022/6/23 21:22
 *
 * @author raven
 */
public class Main {

    public static void main(String[] args) {
        //rotate();
        //remove();
        //testRBTree();
        removeRBTree();
    }

    private static void removeRBTree() {
        Integer[] intArray = new Integer[]{
                66, 56, 31, 78, 3, 96, 4, 50, 2, 8, 34, 40, 73, 24, 99, 83, 46, 22, 80, 17
        };

        RBTree<Integer> tree2 = new RBTree<>();
        for (Integer num : intArray) {
            tree2.add(num);
        }
        BinaryTrees.println(tree2);
        System.out.println("========before===================");
        for (Integer integer : intArray) {
            tree2.remove(integer);
            System.out.println("==========remove "+integer+" after =================");
            BinaryTrees.println(tree2);
        }

    }

    private static void testRBTree(){
        Integer[] intArray = new Integer[]{
                58, 27, 39, 85, 19, 99, 66, 57, 14, 16, 97, 1, 29, 73, 4, 25, 40, 79, 18
        };

        RBTree<Integer> tree2 = new RBTree<>();
        for (Integer num : intArray) {
            tree2.add(num);
        }
        BinaryTrees.println(tree2);
        //for (Integer num : intArray) {
        //    tree2.remove(num);
        //    BinaryTrees.println(tree2);
        //    System.out.println("==============================================");
        //}
    }
    private static void remove() {
        Integer[] intArray = new Integer[]{
                7, 4, 9, 2, 5, 8, 11, 3, 1
        };

        BST<Integer> tree = new BST<>();
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


    private static void rotate() {
        Integer[] intArray = new Integer[]{
                4, 8, 12, 14, 16, 23, 26, 34, 38, 47, 49, 64, 67, 68, 74, 77, 80, 81, 88, 89, 92, 97
        };

        AVLTree<Integer> tree2 = new AVLTree<>();
        for (Integer num : intArray) {
            tree2.add(num);
        }
        BinaryTrees.println(tree2);
        for (Integer num : intArray) {
            tree2.remove(num);
            BinaryTrees.println(tree2);
            System.out.println("==============================================");
        }


    }
}