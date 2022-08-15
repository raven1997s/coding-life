package com.raven.data_structures_and_algorithms.algorithms.sort.cmp_sort;

import com.raven.data_structures_and_algorithms.algorithms.sort.CountingSort;
import com.raven.data_structures_and_algorithms.algorithms.sort.cmp_sort.entity.Stable;

import java.text.DecimalFormat;

/**
 * Description:
 * date: 2022/8/7 18:45
 *
 * @author raven
 */
public abstract class Sort<E extends Comparable<E>> implements Comparable<Sort<E>> {
    protected E[] array;
    private int cmpCount;
    private int swapCount;
    private long time;
    private final DecimalFormat fmt = new DecimalFormat("#.00");

    public void sort(E[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        this.array = array;
        long begin = System.currentTimeMillis();
        sort();
        time = System.currentTimeMillis() - begin;
    }

    /**
     * 抽象排序接口
     */
    protected abstract void sort();

    /**
     * 比较array[i1] 和 array[i2]的大小
     *
     * @param i1
     * @param i2
     * @return 0 array[i1] == array[i2]
     * 返回值 > 0 array[i1] > array[i2]
     * 返回值 < 0 array[i1] < array[i2]
     */
    protected int cmp(int i1, int i2) {
        // 记录比较次数
        cmpCount++;
        return array[i1].compareTo(array[i2]);
    }

    /**
     * 比较俩个元素的大小
     *
     * @param v1
     * @param v2
     * @return
     */
    protected int cmp(E v1, E v2) {
        // 记录比较次数
        cmpCount++;
        return v1.compareTo(v2);
    }

    protected void swap(int i1, int i2) {
        // 记录交换次数
        swapCount++;
        E temp = array[i1];
        array[i1] = array[i2];
        array[i2] = temp;
    }

    @Override
    public int compareTo(Sort o) {
        int result = (int) (time - o.time);
        if (result != 0) {
            return result;
        }

        result = cmpCount - o.cmpCount;
        if (result != 0) {
            return result;
        }

        return swapCount - o.swapCount;
    }

    @Override
    public String toString() {
        String timeStr = "耗时：" + (time / 1000.0) + "s(" + time + "ms)";
        String compareCountStr = "比较：" + numberString(cmpCount);
        String swapCountStr = "交换：" + numberString(swapCount);
        String stableStr = "稳定性：" + isStable();
        return "【" + getClass().getSimpleName() + "】\n"
                + timeStr + " \t"
                + stableStr + " \t"
                + compareCountStr + "\t "
                + swapCountStr + "\n"
                + "------------------------------------------------------------------";

    }

    /**
     * 校验排序算饭是否具有稳定性
     *
     * @return
     */
    private boolean isStable() {
        if (this instanceof ShellSort || this instanceof CountingSort) {
            return false;
        }
        Stable[] stableArray = new Stable[20];
        for (int i = 0; i < 20; i++) {
            stableArray[i] = new Stable(i * 10, 10);
        }
        sort((E[]) stableArray);
        for (int i = 1; i < stableArray.length; i++) {
            if ((stableArray[i].num - stableArray[i - 1].num) != 10) {
                return false;
            }
        }
        return true;
    }


    private String numberString(int number) {
        if (number < 10000) {
            return "" + number;
        }

        if (number < 100000000) {
            return fmt.format(number / 10000.0) + "万";
        }
        return fmt.format(number / 100000000.0) + "亿";
    }
}