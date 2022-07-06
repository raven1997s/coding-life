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
        remove();
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


}