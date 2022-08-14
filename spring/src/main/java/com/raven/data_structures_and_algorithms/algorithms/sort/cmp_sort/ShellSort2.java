package com.raven.data_structures_and_algorithms.algorithms.sort.cmp_sort;


import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * date: 2022/8/12 16:48
 * 希尔排序
 *
 * @author raven
 */
public class ShellSort2<E extends Comparable<E>> extends Sort<E> {
    @Override
    protected void sort() {
        // 获取步长列表
        List<Integer> stepSequenceList = shellStepSequence();
        //
        for (Integer step : stepSequenceList) {
            sort(step);
        }
    }

    /**
     * 分成step列进行排序
     *
     * @param step 步长
     */
    private void sort(int step) {
        // 对每一列进行排序
        for (int col = 0; col < step; col++) {
            for (int begin = col + step; begin < array.length; begin = step + begin) {
                // 记录当前要开始比较的元素索引位置
                int curr = begin;
                E element = array[curr];
                // 当前元素和前一个元素逐个比较 如果小于前一个元素 则进行交换
                while (curr > col && cmp(element, array[curr - step]) < 0) {
                    array[curr] = array[curr - step];
                    // 更新当前元素的索引位置
                    curr = curr - step;
                }
                array[curr] = element;
            }
        }
    }

    /**
     * 希尔步长列表
     *
     * @return
     */
    private List<Integer> shellStepSequence() {
        ArrayList<Integer> stepSequence = new ArrayList<>();
        int step = array.length;
        // length = 32  step = 16 8 4 2 1
        while ((step = step >> 1) > 0) {
            stepSequence.add(step);
        }
        return stepSequence;
    }
}