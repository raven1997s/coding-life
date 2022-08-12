package com.raven.data_structures_and_algorithms.algorithms.base_01_sort;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Description:
 * date: 2022/8/12 16:48
 * 希尔排序
 *
 * @author raven
 */
public class ShellSort<E extends Comparable<E>> extends Sort<E> {
    @Override
    protected void sort() {
        // 获取步长列表
        List<Integer> stepSequenceList = sedgewickStepSequence();
        // 将列表根据步长进行多次排序
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
            // 从第二行开始与该列上一行元素开始逐个比较，如果小于前一个元素，则进行交换，直到比较完前面的所有元素
            // 元素在数组中的索引是index = col + row * step
            // col col+step  col + 2*step
            // begin 初始值为第二行第col个元素
            for (int begin = col + step; begin < array.length; begin = step + begin) {
                // 记录当前要开始比较的元素索引位置
                int curr = begin;
                // 当前元素和前一个元素逐个比较 如果小于前一个元素 则进行交换
                // 要比较位置的索引不能小于列的数值
                while (curr > col && cmp(curr, curr - step) < 0) {
                    swap(curr, curr - step);
                    // 更新当前元素的索引位置
                    curr = curr - step;
                }
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


    private List<Integer> sedgewickStepSequence() {
        List<Integer> stepSequence = new LinkedList<>();
        int k = 0, step = 0;
        while (true) {
            if (k % 2 == 0) {
                int pow = (int) Math.pow(2, k >> 1);
                step = 1 + 9 * (pow * pow - pow);
            } else {
                int pow1 = (int) Math.pow(2, (k - 1) >> 1);
                int pow2 = (int) Math.pow(2, (k + 1) >> 1);
                step = 1 + 8 * pow1 * pow2 - 6 * pow2;
            }
            if (step >= array.length) {
                break;
            }
            stepSequence.add(0, step);
            k++;
        }
        return stepSequence;
    }
}