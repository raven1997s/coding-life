package com.raven.data_structures_and_algorithms.structure.base_12_priorityqueue.heap;

/**
 * Description:
 * date: 2022/7/28 14:03
 *
 * @author raven
 */
public interface Heap<E> {

    /**
     * 获取堆中元素个数
     * @return
     */
    int size();

    /**
     * 判断堆是否为null
     * @return
     */
    boolean isEmpty();

    /**
     * 清楚堆中所有元素
     */
    void clear();

    /**
     * 添加元素
     * @param
     */
    void add(E element);

    /**
     * 获取堆顶元素
     * @return
     */
    E get();

    /**
     * 删除堆顶元素
     * @return
     */
    E remove();

    /**
     * 删除堆顶元素 并用添加新顶元素放到堆顶
     * @param e
     * @return
     */
    E replace(E e);
}