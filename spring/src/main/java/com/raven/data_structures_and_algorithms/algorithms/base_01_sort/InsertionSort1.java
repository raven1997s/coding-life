package com.raven.data_structures_and_algorithms.algorithms.base_01_sort;

/**
 * Description:
 * date: 2022/8/8 15:13
 * 插入排序 (升序)
 *
 * @author raven
 */
public class InsertionSort1<E extends Comparable<E>> extends Sort<E> {
    @Override
    protected void sort() {
        // 从第二个元素开始逐个和前面的元素比较，如果小于前一个元素，则进行交换，直到比较完前面的所有元素
        for (int begin = 1; begin < array.length; begin++) {
            // 记录当前要开始比较的元素索引位置
            int curr = begin;
            // 当前元素和前一个元素逐个比较 如果小于前一个元素 则进行交换
            while (curr > 0 && cmp(curr, curr - 1) < 0) {
                swap(curr, curr - 1);
                // 更新当前元素的索引位置
                curr--;
            }
        }
    }
}