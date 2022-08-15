package com.raven.data_structures_and_algorithms.algorithms.sort.cmp_sort;

import com.raven.data_structures_and_algorithms.algorithms.sort.CountingSort;
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
@SuppressWarnings({"rawtypes", "unchecked"})
public class Main {
    public static void main(String[] args) {
        Integer[] array = {7, 3, 5, 8, 6, 7, 4, 5};
        //Integer[] array = Integers.random(50000, 1, 50000);
        //Integer[] array = Integers.ascOrder( 1, 20000);
        //Integer[] array = Integers.tailAscOrder(1, 20000, 3000);
        //Integer[] array1 = Integers.copy(array);
        //Integer[] array2 = Integers.copy(array);
        //heapSortTest(array);
        //selectionSortTest(array1);
        //bubbleSort2Test(array2);

        testSorts(array,
                new CountingSort()
                //new ShellSort2(),
                //new MergeSort(),
                //new QuickSort(),
                //new InsertionSort3(),
                //new BubbleSort2(),
                //new SelectionSort(),
                //new HeapSort()
        );
        //testBinarySearchIndexOf();
        //testBinarySearchSearch();
    }

    static void testBinarySearchSearch() {
        Asserts.test(BinarySearch.search(new int[]{2, 4, 5, 6, 8, 10}, 2) == 1);
        Asserts.test(BinarySearch.search(new int[]{2, 4, 5, 6, 8, 10}, 3) == 1);
        Asserts.test(BinarySearch.search(new int[]{2, 4, 5, 6, 8, 10}, 10) == 6);
        Asserts.test(BinarySearch.search(new int[]{2, 4, 5, 6, 8, 10}, 9) == 5);
        Asserts.test(BinarySearch.search(new int[]{2, 4, 5, 6, 8, 10}, 11) == 6);
    }

    static void testBinarySearchIndexOf() {
        Asserts.test(BinarySearch.indexOf(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, 6) == 5);
        Asserts.test(BinarySearch.indexOf(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, 1) == 0);
        Asserts.test(BinarySearch.indexOf(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, 15) == 14);
        Asserts.test(BinarySearch.indexOf(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16}, 15) == -1);
    }

    static void testSorts(Integer[] array, Sort... sorts) {
        for (Sort sort : sorts) {
            Integer[] copy = Integers.copy(array);
            Integers.println(copy);
            sort.sort(copy);
            Asserts.test(Integers.isAscOrder(copy));
            Integers.println(copy);

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