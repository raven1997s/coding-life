package com.raven.algorithm.structure.base_06_tree.restructure;

import com.raven.algorithm.structure.base_05_binarysearchtree.*;
import com.raven.algorithm.structure.utils.printer.BinaryTrees;
import org.apache.commons.lang3.RandomUtils;

/**
 * Description:
 * date: 2022/6/23 21:22
 *
 * @author raven
 */
public class Main {

    public static void main(String[] args) {
        rotate();
        //remove();
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