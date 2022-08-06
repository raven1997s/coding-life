package com.raven.data_structures_and_algorithms.algorithms.base_01_排序;

import com.google.common.collect.Lists;
import com.raven.data_structures_and_algorithms.structure.base_03_stack.List;

import java.util.Arrays;
import java.util.Collections;

/**
 * Description:
 * date: 2022/8/6 21:12
 * 冒泡排序 升序
 *
 * @author raven
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] ints = new int[]{2, 43, 35, 32, 55, 63, 90, 9};
        sort(ints);
    }

    static void sort(int[] ints) {

        // 外循环控制比较的次数
        // 内循环是比较交换元素 已经有序的元素不再比较
        // 内循环每次遍历后会将最大的元素放到最后，所以外循环每次需要对比的元素都少一个
        for (int end = ints.length - 1; end > 0; end--) {
            // 从第二个元素开始和前一个元素俩俩比较，如果当前元素比前一个元素小就交换位置
            // 已经有序的元素不再比较
            for (int begin = 1; begin <= end; begin++) {
                if (ints[begin] < ints[begin - 1]) {
                    int temp = ints[begin];
                    ints[begin] = ints[begin - 1];
                    ints[begin - 1] = temp;
                }
            }
        }
        Arrays.stream(ints).forEach(System.out::println);
    }
}