package com.raven.algorithm.structure.base_05_binarysearchtree;

import com.raven.algorithm.structure.utils.printer.BinaryTrees;

import java.util.Comparator;

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
        comparator();
    }

    private static void compareInteger() {
        Integer[] intArray = new Integer[]{
                7, 4, 9, 2, 5, 8, 11, 3 ,12 ,1
        };
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (Integer num : intArray) {
            tree.add(num);
        }

        BinaryTrees.println(tree);
    }

    static void comparable() {
        BinarySearchTreeExtentComparable<Order> orderBinarySearchTree = new BinarySearchTreeExtentComparable<>();
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

        orderBinarySearchTree.add(new Order(16));
        orderBinarySearchTree.add(new Order(11));
        orderBinarySearchTree.add(new Order(9));
        orderBinarySearchTree.add(new Order(12));
        orderBinarySearchTree.add(new Order(8));
        orderBinarySearchTree.add(new Order(17));
        orderBinarySearchTree.add(new Order(13));
        BinaryTrees.println(orderBinarySearchTree);

    }


}