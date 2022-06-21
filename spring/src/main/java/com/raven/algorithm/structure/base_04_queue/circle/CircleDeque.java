package com.raven.algorithm.structure.base_04_queue.circle;

import com.raven.algorithm.structure.base_04_queue.list.LinkedList;
import com.raven.algorithm.structure.base_04_queue.list.List;

/**
 * Description:
 * date: 2022/6/18 21:50
 *
 * @author raven
 */
public class CircleDeque<E> {

    private int size;
    private E[] elements;
    /**
     * 队头元素的索引
     */
    private int front;

    private static final int DEFAULT_CAPACITY = 10;

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[index(i)] = null;
        }
        size = 0;
        front = 0;
    }

    public CircleDeque() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 从队头入队
     *
     * @param e
     */
    public void enQueueFront(E e) {
        ensureCapacity(size + 1);
        // 计算出对头元素的真实元素 并赋值给front
        front = index(-1);
        elements[front] = e;
        size++;
    }

    /**
     * 从队头出队
     *
     * @return
     */
    public E deQueueFront() {
        E frontElement = elements[front];
        elements[front] = null;
        front = index(1);
        size--;
        return frontElement;
    }

    /**
     * 获取队头元素
     *
     * @return
     */
    public E front() {
        return elements[front];
    }

    /**
     * 从队尾入队
     *
     * @param e
     */
    public void enQueueRear(E e) {
        ensureCapacity(size + 1);
        elements[index(size)] = e;
        size++;
    }

    /**
     * 从队尾出队
     *
     * @return
     */
    public E deQueueRear() {
        // 获取队尾元素的真实索引
        int index = index(size - 1);
        E realElement = elements[index];
        elements[index] = null;
        size--;
        return realElement;
    }

    /**
     * 获取队尾元素
     *
     * @return
     */
    public E rear() {
        return elements[index(size - 1)];
    }

    /**
     * 确保有足够的容量存储元素
     *
     * @param capacity
     */
    private void ensureCapacity(int capacity) {
        // 获取原本的容量 如果新的元素数 >  原本的容量则需要扩容
        int oldCapacity = elements.length;
        if (oldCapacity > capacity) {
            return;
        }
        // 扩容 扩容需要copy原来的数组为一个新的数组
        // 计算扩容的数量  扩容1/5倍
        capacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            // (i + front) % elements.length  找到每个元素的真实索引位置
            // 将扩容后的元素位置重置，从0开始设置
            newElements[i] = elements[index(i)];
        }
        elements = newElements;
        // 重置队头元素的索引
        front = 0;
        System.out.println(oldCapacity + "扩容为" + capacity);
    }

    /**
     * 之前的索引映射到现在循环数组的真实索引
     *
     * @param index
     * @return
     */
    public int index(int index) {
        index = index + front;
        if (index < 0) {
            return index + elements.length;
        }
        return index - (index >= elements.length ? elements.length : 0);
        // index >= elements.length  index < 2 * elements.length
        // index % elements.length ==> index - (index >= elements.length ? elements.length : 0)
        //return index % elements.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CircleQueue{size = ").append(size).append(",front = ").append(front).append(",capcity = ").append(elements.length).append(", elements=[");
        for (int i = 0; i < elements.length; i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(elements[i]);
        }
        sb.append("]}");

        return sb.toString();
    }
}