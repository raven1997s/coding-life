package com.raven.algorithm.structure.base_11_heap;

import com.raven.algorithm.structure.utils.printer.BinaryTrees;
import com.sun.source.tree.BinaryTree;

import java.util.Comparator;

/**
 * Description:
 * date: 2022/7/30 18:03
 * n个元素找出最大的前k个元素
 *
 * @author raven
 */
public class TopK {

    public static void main(String[] args) {
        // 建立一个小顶堆
        BinaryHeap<Integer> heap = new BinaryHeap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        int[] ints = new int[]{
                3, 85, 96, 58, 23, 89, 62, 92, 86, 1, 41, 18, 48, 88, 64, 80, 34, 15, 5, 68
        };

        // 前k哥元素
        int k = 5;
        // 遍历扫描所有元素
        for (int i = 0; i < ints.length; i++) {
            if (i < k) {
                heap.add(ints[i]);
            } else if (ints[i] > heap.get()) {
                // 如果元素大于堆顶元素，就进行替换
                heap.replace(ints[i]);
            }
        }

        BinaryTrees.println(heap);
    }
}