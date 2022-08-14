package com.raven.data_structures_and_algorithms.algorithms.sort.cmp_sort;

/**
 * Description:
 * date: 2022/8/8 15:13
 * 插入排序 (升序)
 * 优化：通过二分搜索的方式找到合适的插入位置，减少比较的次数
 *
 * @author raven
 */
public class InsertionSort3<E extends Comparable<E>> extends Sort<E> {
    @Override
    protected void sort() {
        // 从第二个元素开始逐个和前面的元素比较，提前备份当前元素，
        // 前一个元素大于当前元素时，将前一个元素挪动到当前索引位置
        // 直到比较完前面的所有元素 将元素放到最后到索引位置
        for (int begin = 1; begin < array.length; begin++) {
            // 获取要插入的元素值
            //E element = array[begin];
            //// 找到要插入的位置
            //int insertIndex = search(begin);
            //// 将要插入的位置开始到begin前一个的所有元素向后移动一位 [insertIndex,begin)
            //for (int i = begin - 1; i >= insertIndex; i--) {
            //    array[i + 1] = array[i];
            //}
            //// 把要插入的元素放到该位置
            //array[insertIndex] = element;

            insert(begin, search(begin));
        }
    }

    private void insert(int source, int dest) {
        // 获取要插入的元素值
        E element = array[source];
        // 将要插入的位置开始到begin前一个的所有元素向后移动一位 [insertIndex,begin)
        if (source - dest >= 0) {
            System.arraycopy(array, dest, array, dest + 1, source - dest);
        }
        // 把要插入的元素放到该位置
        array[dest] = element;
    }

    /**
     * 找到V的在有序数组中的带插入的位置
     * <p>
     * 二分搜索返回的插入位置：第1个大于v的元素位置
     * 利用二分搜索找到 index 位置元素的待插入位置
     * 已经排好序数组的区间范围是 [0, index)
     *
     * @param index
     * @return 返回要插入元素的位置
     */
    private int search(int index) {
        // 定义筛选的范围[begin，end)
        // 二分查找后重新划定筛选范围
        int begin = 0;
        int end = index;
        while (begin < end) {
            // 取出中间值
            int mid = (begin + end) >> 1;
            // 要插入的元素小于中间值 则说明要插入的元素的插入位置在mid左边
            if (cmp(array[index], array[mid]) < 0) {
                end = mid;
            } else {
                // 要出入的元素大于等于中间值 则说明要插入的元素的插入位置在mid右边
                begin = mid + 1;
            }
        }
        // 循环结束 begin == end 为待传入元素的位置
        return begin;
    }
}