package com.raven.data_structures_and_algorithms.algorithms.base_01_排序;

/**
 * Description:
 * date: 2022/8/8 15:13
 * 插入排序 (升序)
 * 优化：将交换改为挪动
 *
 * @author raven
 */
public class InsertionSort2<E extends Comparable<E>> extends Sort<E> {
    @Override
    protected void sort() {
        // 从第二个元素开始逐个和前面的元素比较，提前备份当前元素，
        // 前一个元素大于当前元素时，将前一个元素挪动到当前索引位置
        // 直到比较完前面的所有元素 将元素放到最后到索引位置
        for (int begin = 1; begin < array.length; begin++) {
            // 记录当前要开始比较的元素索引位置
            int curr = begin;
            E element = array[curr];
            // 当前元素和前一个元素逐个比较 如果小于前一个元素 则进行交换
            while (curr > 0 && cmp(element, array[curr - 1]) < 0) {
                array[curr] = array[curr - 1];
                // 更新当前元素的索引位置
                curr--;
            }
            array[curr] = element;
        }
    }
}