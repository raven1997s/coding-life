package com.raven.data_structures_and_algorithms.algorithms.base_01_sort;


/**
 * Description:
 * date: 2022/8/6 21:12
 * 冒泡排序 升序
 * 优化：如果数组已经部分有序，则记录有序的位置，减少循环比较有序的数据
 *
 * @author raven
 */
public class BubbleSort2<E extends Comparable<E>> extends Sort<E> {
    @Override
    protected void sort() {
        // 外循环控制比较的次数
        // 内循环是比较交换元素 已经有序的元素不再比较
        // 内循环每次遍历后会将最大的元素放到最后，所以外循环每次需要对比的元素都少一个
        for (int end = array.length - 1; end > 0; end--) {
            // 默认一开始所有元素就全部有顺序
            int sortedIndex = 1;
            // 从第二个元素开始和前一个元素俩俩比较，如果当前元素比前一个元素小就交换位置
            // 已经有序的元素不再比较
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                    // 记录进行了比较的index位置
                    sortedIndex = begin;
                }
            }
            // 设置目前已经有序的索引位置，下次外循环直接比较到该位置即可
            end = sortedIndex;

        }
    }
}