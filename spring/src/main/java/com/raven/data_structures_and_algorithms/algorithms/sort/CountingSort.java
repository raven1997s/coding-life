package com.raven.data_structures_and_algorithms.algorithms.sort;

import com.raven.data_structures_and_algorithms.algorithms.sort.cmp_sort.Sort;

/**
 * Description:
 * date: 2022/8/13 19:53
 *  计数排序
 * @author raven
 */
public class CountingSort extends Sort<Integer> {
    @Override
    protected void sort() {
        // 默认第一个元素就是数组中最大的元素
        int max = array[0];
        // 找到数组中最大的元素 并创建计数排序的数组
        for (int i = 1; i < array.length; i++) {
            if (max > array[i]){
                max = array[i];
            }
        }

        // 统计元素出现的次数
        final int[] counts = new int[max];
        for (Integer integer : array) {
            // array元素的值就是counts的索引
            // 取出array元素，将count数组对应索引位置的元素个数++
            counts[integer]++;
        }

        // 按顺序赋值
        // 定义array数组的指针，每次赋值结束后向后移动
        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            // counts
            while (counts[i] -- > 0){
                //
                array[index++] = i;
            }
        }


    }
}