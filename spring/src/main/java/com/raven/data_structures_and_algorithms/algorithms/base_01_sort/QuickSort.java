package com.raven.data_structures_and_algorithms.algorithms.base_01_sort;

/**
 * Description:
 * date: 2022/8/11 21:54
 * 快速排序
 *
 * @author raven
 */
public class QuickSort<E extends Comparable<E>> extends Sort<E> {
    @Override
    protected void sort() {
        sort(0, array.length);
    }

    /**
     * 对[begin，end) 范围内对元素进行快速排序
     *
     * @param begin
     * @param end
     */
    private void sort(int begin, int end) {
        // 如果元素个数为1个或者0个，则不再需要排序
        if (end - begin < 2) {
            return;
        }
        // 找到轴点的索引位置
        int mid = pivotIndex(begin, end);
        // [begin,x,x,mid,mid+1,x,end]  轴点 mid  轴点左边的元素 [begin,mid) 轴点右边的元素 [mid + 1,begin)
        // 对轴点左边的元素进行快速排序 [begin,mid)
        sort(begin, mid);
        // 对轴点右边的元素进行快速排序 [mid + 1,begin)
        sort(mid + 1, end);
    }

    /**
     * 构建出[begin,end)范围的轴点元素
     *
     * @param begin
     * @param end
     * @return 轴点的最终位置
     */
    private int pivotIndex(int begin, int end) {
        // 如果轴点左右元素数量极度不均衡，会导致快排的时间复杂度变为O(n) ，可以通过随机选择轴点的方式降低最坏情况出现的概率
        // 随机将begin和[begin,end)范围内的一个元素交换实现随机选择轴点
        swap(begin, begin + (int) (Math.random() * (end - begin)));
        // 默认begin为轴点元素，先备份begin索引位置的元素
        E pivot = array[begin];
        // end -- 把end指向范围内的最后一个元素的位置
        end--;
        // 从右边开始将元素挨个和轴点元素比较 直到比较完所有的元素
        while (begin < end) {
            // 从右向左逐个和轴点比较
            while (begin < end) {
                // 如果右边的元素大于轴点元素 ，则将end -- 继续和下一个元素比较
                if (cmp(pivot, array[end]) < 0) {
                    end--;
                } else {
                    // 如果右边的元素小于等于轴点元素，则将end位置的元素放到begin的位置，然后begin向右移动一位
                    // 然后掉头改为从左边begin位置开始向右逐渐和轴点元素比较
                    array[begin++] = array[end];
                    break;
                }
            }
            // 从左向右逐个和轴点比较
            while (begin < end) {
                // 如果左边的元素小于轴点 则begin++ 继续和下一个元素比较
                if (cmp(pivot, array[begin]) > 0) {
                    begin++;
                } else {
                    // 如果左边的元素大于等于轴点元素，则把begin位置的元素放到end的位置，然后把end向左移动一位
                    // 然后掉头改为从右边end位置开始向左逐渐和轴点元素比较
                    array[end--] = array[begin];
                    break;
                }
            }
        }

        // 循环结束后，把元素放到轴点最终的位置
        array[begin] = pivot;
        // begin = end = 轴点的最终位置
        return begin;
    }
}