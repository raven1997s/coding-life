package com.raven.algorithm.structure.base_11_heap;

import com.raven.algorithm.structure.utils.printer.BinaryTreeInfo;
import com.raven.algorithm.structure.utils.printer.BinaryTrees;

/**
 * Description:
 * date: 2022/7/28 21:43
 *
 * @author raven
 */
public class Main {
    public static void main(String[] args) {
        crudTest();
    }

    static void crudTest() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(29);
        heap.add(32);
        heap.add(16);
        heap.add(48);
        heap.add(13);
        heap.add(40);
        heap.add(33);
        heap.add(63);
        heap.add(28);
        heap.add(1);
        BinaryTrees.println(heap);

        System.out.println("==========>" + heap.remove());
        BinaryTrees.println(heap);

        System.out.println("==========>" + heap.remove());
        BinaryTrees.println(heap);

        System.out.println("==========>" + heap.remove());
        BinaryTrees.println(heap);
    }

}