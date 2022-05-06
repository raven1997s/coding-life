package com.raven.algorithm.dynamicarray;

import java.util.Arrays;

/**
 * Description:
 * date: 2022/4/30 10:39
 * 自定义 ArrayList (正数版)
 *
 * @author raven
 */
public class ArrayList<E> {
    private int size;
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 16;
    private static final int NOT_FOUND_ELEMENT = -1;

    public ArrayList(int capaticy) {
        capaticy = capaticy <= 0 ? DEFAULT_CAPACITY : capaticy;
        elements = (E[]) new Object[capaticy];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * @return 数组的长度
     */
    public int size() {
        return size;
    }

    /**
     * 在末尾添加一个元素
     *
     * @param element
     */
    public void add(E element) {
        add(element, size);
    }

    /**
     * 在index的位置插入一个元素
     *
     * @param element
     * @param index
     */
    public void add(E element, int index) {
        rangeCheckForAdd(index);

        // 根据最新的元素个数来判断是否需要扩容
        ensureCapacity(size + 1);
        // 0 1 2 3 4
        // 1 2 3 4 5
        // 将原index ～ size - 1 的所有数据 向后移一位
        for (int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }
        elements[index] = element;
        size++;
    }

    /**
     * 删除指定索引的元素
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        // 0 1 2 3 4
        // 1 2 3 4 5
        // 将原index ～ size - 1 的所有数据 向前移动一位
        rangeCheck(index);
        E oldElement = elements[index];
        for (int i = index + 1; i <= size - 1; i++) {
            elements[i - 1] = elements[i];
        }
        size--;
        return oldElement;
    }

    /**
     * 判断列表中是否包含指定元素
     *
     * @param element
     * @return
     */
    public boolean contains(E element) {
        return indexOf(element) != NOT_FOUND_ELEMENT;
    }

    /**
     * 获取指定位置的元素
     *
     * @param index
     * @return
     */
    public E get(int index) {
        rangeCheck(index);
        return elements[index];
    }

    /**
     * 设置指定位置的元素为element
     *
     * @param index   替换index位置的元素
     * @param element 要替换的元素
     * @return 被替换的元素值
     */
    public E set(int index, E element) {
        rangeCheck(index);

        E old = elements[index];
        elements[index] = element;
        return old;
    }

    /**
     * 清空列表
     */
    public void clear() {
        size = 0;
    }

    /**
     * 判断列表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 返回指定element的位置
     *
     * @param element
     * @return
     */
    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element) {
                return i;
            }
        }
        return NOT_FOUND_ELEMENT;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("index :" + index + ", size: " + size);
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("index :" + index + ", size: " + size);
        }
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
            newElements[i] = elements[i];
        }
        elements = newElements;
        System.out.println(oldCapacity + "扩容为" + capacity);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ArrayList{size=").append(size).append(", elements=[");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(elements[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}