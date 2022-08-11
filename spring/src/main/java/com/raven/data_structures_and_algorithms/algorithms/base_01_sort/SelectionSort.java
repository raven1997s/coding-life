package com.raven.data_structures_and_algorithms.algorithms.base_01_sort;

/**
 * Description:
 * date: 2022/8/7 17:48
 * 选择排序 升序
 *
 * @author raven
 */
public class SelectionSort<E extends Comparable<E>> extends Sort<E> {

    @Override
    protected void sort() {
        // 将每一个元素和其他元素进行比较，比较结束后将最大的元素和本轮的末尾元素进行交换
        // 外循环代表比较元素的个数，每一轮减少一个
        for (int end = array.length - 1; end > 0; end--) {
            int maxIndex = 0;
            // 逐个元素比较，找到本轮进行比较的元素中中最大的元素
            for (int begin = 1; begin <= end; begin++) {
                // 如果当前元素大于等于 最大的元素 则记录最大元素的索引
                //if (array[maxIndex] <= array[begin]) {
                if (cmp(maxIndex, begin) <= 0) {
                    maxIndex = begin;
                }
            }

            // 交换最大的数据到最后
            swap(maxIndex, end);
        }
    }
}