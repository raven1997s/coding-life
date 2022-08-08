package com.raven.data_structures_and_algorithms.algorithms.base_01_排序;

/**
 * Description:
 * date: 2022/8/7 18:31
 * 堆排序
 * 堆排序可以认为是对选择排序的一种优化
 *
 * @author raven
 */
public class HeapSort extends Sort {
    private int heapSize;

    @Override
    protected void sort() {
        // 原地建堆
        // 自下而上的下滤
        // 从最后一个非叶子节点索引开始
        heapSize = array.length;
        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }

        while (heapSize > 1) {
            // 将堆顶元素和堆最后堆元素进行交换
            // 将堆堆size -1
            swap(0, --heapSize);
            // 将0位置堆元素下滤
            siftDown(0);
        }

    }

    private void siftDown(int index) {
        // 获取要下滤的元素
        Integer element = array[index];

        // 如果元素有子节点，则和子节点比较，判断是否需要交换
        // 从第一个叶子节点开始，后续的节点都是叶子节点，也没有字节点
        // 获取第一个叶子节点的索引 第一个叶子节点的索引 = 非叶子节点的数量
        int half = heapSize >> 1;
        // 没有子节点就停止比较下滤
        while (index < half) {
            // 二叉堆是完全二叉树，index的节点只有俩种情况
            // 1。有左子节点
            // 2。有左子节点也有右子节点
            // 默认和左子节点比较，如果有右子节点，选出子节点中更大的哪个
            // 左子节点的索引为 2i+1
            int childIndex = (index << 1) + 1;
            Integer childElement = array[childIndex];
            // 右子节点的索引为 2i+2
            int rightIndex = childIndex + 1;
            // 如果存在右子节点 并且右子节点还大于左子节点，则替换childIndex 和childElement
            if (rightIndex < heapSize && cmpElement(array[rightIndex], childElement) > 0) {
                childIndex = rightIndex;
                childElement = array[rightIndex];
            }

            // 节点元素和子节点进行比较，如果大于等于子节点 则不再需要下滤
            if (cmpElement(element, childElement) >= 0) {
                break;
            }

            // 把子节点放到index 的位置
            array[index] = childElement;
            // 把子节点的索引给到节点 循环结束后把元素放到index 的位置
            index = childIndex;
        }

        array[index] = element;
    }
}