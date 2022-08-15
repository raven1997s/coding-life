package com.raven.data_structures_and_algorithms.algorithms.sort;

import com.raven.data_structures_and_algorithms.algorithms.sort.cmp_sort.Sort;

/**
 * Description:
 * date: 2022/8/13 19:53
 * 计数排序
 *
 * @author raven
 */
public class CountingSort extends Sort<Integer> {
    @Override
    protected void sort() {
        // 找到数组中的最值
        int max = array[0];
        int min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }

        // 统计元素出现的次数
        int[] counts = new int[max - min + 1];
        for (int i = 0; i < array.length; i++) {
            // 节约内存空间 从第一个有元素的位置开始存储元素
            counts[array[i] - min]++;
        }

        // 每个元素累加上其前面的所有元素
        //得到的就是元素在有序序列中的位置信息
        for (int i = 1; i < counts.length; i++) {
            // 元素数量= 前一个的值 + 当前值
            counts[i] = counts[i - 1] + counts[i];
        }

        // 开辟新的内存空间 将元素放到合适的位置
        // 倒序遍历 确保稳定性
        int[] newArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            //// 获取array元素在counts中的索引
            //Integer index = array[i]  - min;
            //// 元素前面存在的个数减少一个
            //counts[index] = counts[index] -1;
            //// 将元素放入有序数组合适的位置
            //newArray[counts[index]] = array[i];
            newArray[--counts[array[i] - min]] = array[i];
        }

        // 用newArray覆盖array
        for (int i = 0; i < newArray.length; i++) {
            array[i] = newArray[i];
        }

    }

    protected void sort0() {
        // 默认第一个元素就是数组中最大的元素
        int max = array[0];
        // 找到数组中最大的元素 并创建计数排序的数组
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        // 统计元素出现的次数
        final int[] counts = new int[max + 1];
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
            while (counts[i]-- > 0) {
                //
                array[index++] = i;
            }
        }
    }
}