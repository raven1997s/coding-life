package com.raven.data_structures_and_algorithms.structure.base_12_priorityqueue.queue;

import com.raven.data_structures_and_algorithms.structure.base_12_priorityqueue.heap.BinaryHeap;

import java.util.Comparator;

/**
 * Description:
 * date: 2022/7/31 10:39
 * 优先级队列
 * @author raven
 */
public class PriorityQueue<E> {

    private final BinaryHeap<E> heap;

    public PriorityQueue() {
        this(null);
    }

    public PriorityQueue(Comparator<E> comparator) {
        heap = new BinaryHeap<>(comparator);
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public void clear() {
        heap.clear();
    }

    /**
     * 入队
     *
     * @param e
     */
    public void enQueue(E e) {
        heap.add(e);
    }

    /**
     * 出队
     *
     * @return
     */
    public E deQueue() {
        return heap.remove();
    }

    /**
     * 获取队头元素
     *
     * @return
     */
    public E front() {
        return heap.get();
    }
}