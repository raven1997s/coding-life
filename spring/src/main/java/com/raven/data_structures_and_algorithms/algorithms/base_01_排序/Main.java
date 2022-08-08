package com.raven.data_structures_and_algorithms.algorithms.base_01_排序;

import com.raven.utils.Asserts;
import com.raven.utils.Integers;
import com.raven.utils.Times;

import java.util.Arrays;

/**
 * Description:
 * date: 2022/8/7 19:39
 *
 * @author raven
 */
public class Main {
    public static void main(String[] args) {
        Integer[] array = Integers.random(10000, 1, 20000);
        //Integer[] array = Integers.ascOrder( 1, 20000);
        //Integer[] array = Integers.tailAscOrder(1, 20000, 3000);
        //Integer[] array1 = Integers.copy(array);
        //Integer[] array2 = Integers.copy(array);
        //heapSortTest(array);
        //selectionSortTest(array1);
        //bubbleSort2Test(array2);

        testSorts(array, new BubbleSort2(), new SelectionSort(), new HeapSort(), new BubbleSort(), new BubbleSort1());
    }

    static void testSorts(Integer[] array, Sort... sorts) {
        for (Sort sort : sorts) {
            sort.sort(Integers.copy(array));
        }
        Arrays.sort(sorts);
        for (Sort sort : sorts) {
            System.out.println(sort);
        }
    }

    static void selectionSortTest(Integer[] array) {
        Times.test("selectionSortTest", () -> new SelectionSort().sort(array));
    }

    static void bubbleSortTest(Integer[] array) {
        Times.test("bubbleSortTest", () -> new BubbleSort().sort(array));
    }

    static void bubbleSort1Test(Integer[] array) {
        Times.test("bubbleSort1Test", () -> new BubbleSort1().sort(array));
    }

    static void bubbleSort2Test(Integer[] array) {
        Times.test("bubbleSort2Test", () -> new BubbleSort2().sort(array));
    }

    static void heapSortTest(Integer[] array) {
        Times.test("heapSortTest", () -> new HeapSort().sort(array));
    }
}