package com.raven.algorithm.dynamicarray;

import java.util.Arrays;

/**
 * Description:
 * date: 2022/4/30 10:39
 * 自定义 ArrayList (正数版)
 *
 * @author raven
 */
public class ArrayList {
    private int size;
    private int[] elements;
    private static final int DEFAULT_CAPACITY = 16;
    private static final int NOT_FOUND_ELEMENT = -1;

    public ArrayList(int capaticy) {
        capaticy = capaticy <= 0 ? DEFAULT_CAPACITY : capaticy;
        elements = new int[capaticy];
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
    public void add(int element) {
        add(element, size);
    }

    /**
     * 在index的位置插入一个元素
     *
     * @param element
     * @param index
     */
    public void add(int element, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("index :" + index + ", size: " + size);
        }
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
    public int remove(int index) {
        // 0 1 2 3 4
        // 1 2 3 4 5
        // 将原index ～ size - 1 的所有数据 向前移动一位
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("index :" + index + ", size: " + size);
        }
        int oldElement = elements[index];
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
    public boolean contains(int element) {
        return indexOf(element) != NOT_FOUND_ELEMENT;
    }

    /**
     * 获取指定位置的元素
     *
     * @param index
     * @return
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("index :" + index + ", size: " + size);
        }
        return elements[index];
    }

    /**
     * 设置指定位置的元素为element
     *
     * @param index   替换index位置的元素
     * @param element 要替换的元素
     * @return 被替换的元素值
     */
    public int set(int index, int element) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("index :" + index + ", size: " + size);
        }
        int old = elements[index];
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
    public int indexOf(int element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element) {
                return i;
            }
        }
        return NOT_FOUND_ELEMENT;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ArrayList{size=").append(size).append(", elements=[");
        for (int i = 0; i < size; i++) {
            if (i != 0 ){
                sb.append(",");
            }
            sb.append(elements[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}