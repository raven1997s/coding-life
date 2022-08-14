package com.raven.data_structures_and_algorithms.algorithms.sort.cmp_sort;

/**
 * Description:
 * date: 2022/8/8 22:06
 * 二叉搜索
 *
 * @author raven
 */
public class BinarySearch {
    /**
     * 找到V的在有序数组中的索引位置
     *
     * @param array
     * @param v
     * @return
     */
    public static int indexOf(int[] array, int v) {
        if (array == null || array.length == 0) {
            return -1;
        }
        // 定义筛选的范围[begin，end)
        // 二分查找后重新划定筛选范围
        int begin = 0;
        int end = array.length;
        while (begin < end) {
            // 取出中间值
            int mid = (begin + end) >> 1;
            // 如果中间值小于要搜索的值，则证明要搜索的值在右边
            if (array[mid] < v) {
                begin = mid + 1;
            } else if (array[mid] > v) {
                end = mid;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 找到V的在有序数组中的带插入的位置
     *
     * 二分搜索返回的插入位置：第1个大于v的元素位置
     * @param array
     * @param v
     * @return 返回要插入元素的位置
     */
    public static int search(int[] array, int v) {
        if (array == null || array.length == 0) {
            return -1;
        }
        // 定义筛选的范围[begin，end)
        // 二分查找后重新划定筛选范围
        int begin = 0;
        int end = array.length;
        while (begin < end) {
            // 取出中间值
            int mid = (begin + end) >> 1;
            // 要插入的元素小于中间值 则说明要插入的元素的插入位置在mid左边
            if (v < array[mid]) {
                end  = mid;
            } else {
            // 要出入的元素大于等于中间值 则说明要插入的元素的插入位置在mid右边
                begin = mid + 1;
            }
        }
        // 循环结束 begin == end 为待传入元素的位置
        return begin;
    }
}