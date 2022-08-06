package com.raven.data_structures_and_algorithms.structure.base_12_priorityqueue.heap;

import java.util.Comparator;

/**
 * Description:
 * date: 2022/7/28 22:47
 *
 * @author raven
 */
public abstract class AbstractHeap<E> implements Heap<E> {
    protected int size;

    // 外界指定排序规则
    protected Comparator<E> comparator;

    public AbstractHeap(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public AbstractHeap() {
    }

    protected int compare(E e1, E e2) {
        // 如果外界指定了排序方式 就用传入的排序方式进行比较 否则用对象必须实现(Comparable<E>)接口 指定比较器
        return comparator != null ? comparator.compare(e1, e2) : ((Comparable<E>) e1).compareTo(e2);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}