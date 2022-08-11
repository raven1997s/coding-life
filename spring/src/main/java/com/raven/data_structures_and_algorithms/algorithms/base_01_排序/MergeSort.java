package com.raven.data_structures_and_algorithms.algorithms.base_01_排序;

/**
 * Description:
 * date: 2022/8/10 20:18
 * 归并排序
 *
 * @author raven
 */
public class MergeSort<E extends Comparable<E>> extends Sort<E> {

    private E[] leftArray;

    @Override
    protected void sort() {
        // 申请一份内存空间 用于存放比较时copy出来的左边的数组元素
        leftArray = (E[]) new Comparable[array.length >> 1];
        sort(0, array.length);
    }

    /**
     * 对[begin,end)范围内的元素进行归并排序
     *
     * @param begin
     * @param end
     */
    private void sort(int begin, int end) {
        // end - begin 指的是范围内有几个元素 如果元素个数< 2则不再需要排序
        if (end - begin < 2) {
            return;
        }

        // 获取最中间的元素
        int mid = (end + begin) >> 1;
        // 对数组[begin,mid) 进行归并排序
        sort(begin, mid);
        // 对数组[mid,end) 进行归并排序
        sort(mid, end);
        // 合并
        merge(begin, mid, end);
    }

    /**
     * [begin, mid) 和 〔mid，end）范围的序列合并成一个有序序列
     *
     * @param begin
     * @param mid
     * @param end
     */
    private void merge(int begin, int mid, int end) {
        // 定义左边数组的开始索引位置
        int li = 0;
        int le = mid - begin;
        // 定义右边数组的开始索引位置
        int ri = mid;
        int re = end;
        // 定义比较后要把元素放到array的哪个位置的索引
        int ai = begin;

        // 将左边的数组copy一份到内存中 [li,le)
        for (int i = li; i < le; i++) {
            leftArray[i] = array[begin + i];
        }

        // 如果左边没有比较完 需要持续比较
        while (li < le) {
            // 如果ri >= re 正面右边元素已经全部比较结束，只需要将左边剩余元素都移动到右边即可
            if (ri < re && cmp(array[ri], leftArray[li]) < 0) {
                // 如果右边数组元素小，则需要把右边数组移动到ai处 然后 ri ++ ai ++
                array[ai++] = array[ri++];
            } else {
                // 如果左边数组元素小，则需要把左边数组移动到ai处 然后 li ++ ai ++
                array[ai++] = leftArray[li++];
            }
            // cmp位置改为〈= 会失去稳定性
        }
    }
}