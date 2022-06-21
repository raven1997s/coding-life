package com.raven.algorithm.structure.base_04_queue.circle;

import java.util.Arrays;

/**
 * Description:
 * date: 2022/6/20 21:11
 * 循环队列
 *
 * @author raven
 */
@SuppressWarnings("unchecked")
public class CircleQueue<E> {
    private int size;
    private E[] elements;
    /**
     * 队头元素的索引
     */
    private int front;

    private static final int DEFAULT_CAPACITY = 16;

    public CircleQueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 入队
     * 在队列的末尾添加元素
     *
     * @param element
     */
    public void enQueue(E element) {
        ensureCapacity(size + 1);
        // 因为front指向的首个元素的位置可能并不在数组的首个元素，所以入队往队尾插入元素时 需要插入到（数组个数+首个元素的索引位置 % 数组的容量）的位置
        // 将之前的索引转换为真实队列中的索引
        elements[index(size)] = element;
        size++;
    }

    /**
     * 出队 先进先出 队头元素出队
     */
    public E deQueue() {
        E frontElement = elements[front];
        elements[front] = null;
        // 队头元素的位置应该为原队头元素位置+1 再与数组长度取莫
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

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[index(i)] = null;
        }
        size = 0;
        front = 0;
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
        index += front;
        return index - (index >= elements.length ? elements.length : 0);
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