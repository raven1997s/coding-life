package com.raven.algorithm.structure.base_02_linkedlist;

import com.raven.algorithm.structure.base_02_linkedlist.AbstractList;

/**
 * Description:
 * date: 2022/4/30 10:39
 * 自定义 ArrayList (正数版)
 *
 * @author raven
 */
public class ArrayList2<E> extends AbstractList<E> {
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 16;

    public ArrayList2(int capaticy) {
        capaticy = capaticy <= 0 ? DEFAULT_CAPACITY : capaticy;
        elements = (E[]) new Object[capaticy];
    }

    public ArrayList2() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 在index的位置插入一个元素
     *
     * @param element
     * @param index
     */
    @Override
    public void add(E element, int index) {
        rangeCheckForAdd(index);

        /**
         * 因为涉及到元素挪动，index越小，挪动的元素则越多
         * 时间复杂度：
         * 最好：O(1)
         * 最坏：O(n)
         * 平均：O(n)
         */
        // 根据最新的元素个数来判断是否需要扩容
        ensureCapacity(size + 1);
        // 0 1 2 3 4
        // 1 2 3 4 5
        // 将原index ～ size - 1 的所有数据 向后移一位
        //for (int i = size - 1; i >= index; i--) {
        //    elements[i + 1] = elements[i];
        //}
        // 0 1 2 3 4
        // 1 2 3 4 5
        // 将原 > index ～ size 的所有数据 向后移一位
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
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
    @Override
    public E remove(int index) {
        /**
         * 因为涉及到元素挪动，index越小，挪动的元素则越多
         * 时间复杂度：
         * 最好：O(1)
         * 最坏：O(n)
         * 平均：O(n)
         */
        // index   0 1 2 3 4
        // element 1 2 3 4 5
        // 将原index ～ size - 1 的所有数据 向前移动一位
        rangeCheck(index);
        E oldElement = elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        elements[--size] = null;
        // 删除的时候进行缩容判断
        trim();
        return oldElement;
    }

    /**
     * 获取指定位置的元素
     *
     * @param index
     * @return
     */
    @Override
    public E get(int index) { //  时间复杂度O(1)
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
    @Override
    public E set(int index, E element) { //  时间复杂度O(1)
        rangeCheck(index);

        E old = elements[index];
        elements[index] = element;
        return old;
    }

    /**
     * 清空列表
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * 返回指定element的位置
     *
     * @param element
     * @return
     */
    @Override
    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == (element)) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) {
                    return i;
                }
            }
        }
        return NOT_FOUND_ELEMENT;
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
        // 计算扩容的数量  扩容1/2倍
        capacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
        System.out.println(oldCapacity + "扩容为" + capacity);
    }

    /**
     * 缩容 当数组中元素被删除后，数组中元素当数量远小于数组容量，造成空间的浪费 则需要进行缩容
     */
    private void trim() {
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity >> 1;
        // 如果元素的个数大于 缩容一半后新的容量则不需要缩容，也就是当数组元素比容量一般还小当时候，再进行缩容。
        if (size >= newCapacity || newCapacity <= DEFAULT_CAPACITY){
            return;
        }

        // 缩容
        // 创建一个新的数组
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
        System.out.println(oldCapacity + "缩容为：" + newCapacity);
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
        sb.append("]}");
        return sb.toString();
    }
}