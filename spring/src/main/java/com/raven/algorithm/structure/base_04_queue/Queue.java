package com.raven.algorithm.structure.base_04_queue;

import com.raven.algorithm.structure.base_04_queue.list.LinkedList;
import com.raven.algorithm.structure.base_04_queue.list.List;

/**
 * Description:
 * date: 2022/6/18 21:12
 *
 * @author raven
 */
public class Queue<E> {
    private List<E> list = new LinkedList<>();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * 入队
     * @param e
     */
    public void enQueue(E e) {
        list.add(e);
    }

    /**
     * 出队
     * @return
     */
    public E deQueue() {
        return list.remove(0);
    }

    /**
     * 获取队头元素
     * @return
     */
    public E front() {
        return list.get(0);
    }
}