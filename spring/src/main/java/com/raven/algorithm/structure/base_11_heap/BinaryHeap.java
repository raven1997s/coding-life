package com.raven.algorithm.structure.base_11_heap;

import java.util.Comparator;

/**
 * Description:
 * date: 2022/7/28 14:20
 * 二叉堆
 *
 * @author raven
 */
public class BinaryHeap<E> implements Heap<E> {


    private int size;

    // 存放堆元素
    private E[] elements;

    // 外界指定排序规则
    private Comparator<E> comparator;
    /**
     * 数组默认容量 16
     */
    private static final int DEFAULT_CAPACITY = 1 << 4;

    public BinaryHeap() {
        this(null);
    }

    public BinaryHeap(Comparator<E> comparator) {
        this.comparator = comparator;
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public void add(E element) {
        // 参数不能为空校验
        elementNotNullCheck(element);
        // 判断是否需要扩容
        ensureCapacity(size +1);


    }

    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E replace(E e) {
        return null;
    }

    private void emptyCheck() {
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException("heap is empty!");
        }
    }
    private void elementNotNullCheck(E e){
        if (e == null){
            throw new IllegalArgumentException("element must not be null");
        }
    }

    private int compare(E e1, E e2) {
        // 如果外界指定了排序方式 就用传入的排序方式进行比较 否则用对象必须实现(Comparable<E>)接口 指定比较器
        return comparator != null ? comparator.compare(e1, e2) : ((Comparable<E>) e1).compareTo(e2);
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
    }
}