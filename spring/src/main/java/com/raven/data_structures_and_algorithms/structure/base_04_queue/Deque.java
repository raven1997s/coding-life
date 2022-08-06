package com.raven.data_structures_and_algorithms.structure.base_04_queue;

import com.raven.data_structures_and_algorithms.structure.base_04_queue.list.LinkedList;
import com.raven.data_structures_and_algorithms.structure.base_04_queue.list.List;

/**
 * Description:
 * date: 2022/6/18 21:50
 *
 * @author raven
 */
public class Deque<E> {

    private List<E> list = new LinkedList<>();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void clear() {
        list.clear();
    }

    /**
     * 从队头入队
     *
     * @param e
     */
    public void enQueueFront(E e) {
        list.add(e, 0);
    }

    /**
     * 从队头出队
     *
     * @return
     */
    public E deQueueFront() {
        return list.remove(0);
    }

    /**
     * 获取队头元素
     *
     * @return
     */
    public E front() {
        return list.get(0);
    }

    /**
     * 从队尾入队
     *
     * @param e
     */
    public void enQueueRear(E e) {
        list.add(e);
    }

    /**
     * 从队尾出队
     *
     * @return
     */
    public E deQueueRear() {
        return list.remove(list.size() - 1);
    }

    /**
     * 获取队尾元素
     *
     * @return
     */
    public E rear() {
        return list.get(list.size() - 1);
    }
}