package com.raven.data_structures_and_algorithms.algorithms.sort;

import com.raven.data_structures_and_algorithms.algorithms.sort.cmp_sort.Sort;

/**
 * Description:
 * date: 2022/8/16 22:06
 *
 * @author raven
 */
public class RadixSort extends Sort<Integer> {
    @Override
    protected void sort() {
        // 获取最大值
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        //个位数：array[i] / 1% 10 = 3
        //十位数： array[i] 10 % 10 = 9
        //百位数：array[i] 100 % 10 = 5
        //千位数：array[i] 1000 % 10 = 0
        // 对最大值对每一位进行计数排序，从最低位到最高位
        for (int divider = 1; divider <= max; divider *= 10) {
            countingSort(divider);
        }
    }

    private void countingSort(int divider) {
        // 开辟内存空间 记录0-10出现的次数
        int[] counts = new int[10];

        // 统计次数
        for (int i = 0; i < array.length; i++) {
            counts[array[i] / divider % 10]++;
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
            newArray[--counts[array[i]] / divider % 10] = array[i];
        }

        // 用newArray覆盖array
        for (int i = 0; i < newArray.length; i++) {
            array[i] = newArray[i];
        }

    }
}