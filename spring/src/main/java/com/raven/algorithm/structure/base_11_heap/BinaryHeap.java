package com.raven.algorithm.structure.base_11_heap;

import com.raven.algorithm.structure.utils.printer.BinaryTreeInfo;

import java.util.Comparator;

/**
 * Description:
 * date: 2022/7/28 14:20
 * 二叉堆(最大堆)
 * 二叉堆的逻辑结构就是一棵完全二叉树，所以也叫完全二叉堆
 * 鉴于完全二叉树的一些特性，二叉堆的底层（物理结构）一般用数组实现即可
 * 索引i的规律（n是元素数量）
 * 如果i=0，它是根节点
 * 如果i＞0，它的父节点的索引为 floor( ( i-1) / 2)
 * 如果 2i+1≤ n-1，它的左子节点的索引为 2i+1
 * 如果 2i + 1> n-1，它无左子节点
 * 如果 2i+2≤n-1，它的右子节点的索引为 2i+2
 * 如果 2i+2＞n-1，它无右子节点
 *
 * @author raven
 */
public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {

    // 存放堆元素
    private E[] elements;

    /**
     * 数组默认容量 16
     */
    private static final int DEFAULT_CAPACITY = 1 << 4;

    public BinaryHeap() {
        this(null);
    }

    public BinaryHeap(Comparator<E> comparator) {
        super(comparator);
        elements = (E[]) new Object[DEFAULT_CAPACITY];
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
        ensureCapacity(size + 1);
        // size ++ 把元素放到数组最后的位置
        elements[size++] = element;
        // 把新添加的元素上滤处理
        siftUp(size - 1);
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

    // 返回堆顶索引
    @Override
    public Object root() {
        return 0;
    }

    //如果 2i+1≤ n-1，它的左子节点的索引为 2i+1
    @Override
    public Object left(Object node) {
        int index = ((int) node << 1) + 1;
        return index >= size ? null : index;
    }

    //如果 2i+1≤ n-1，它的左子节点的索引为 2i+2
    @Override
    public Object right(Object node) {
        int index = ((int) node << 1) + 2;
        // index >= size 说明越界了
        return index >= size ? null : index;
    }

    @Override
    public Object string(Object node) {
        return elements[(int) node];
    }

    /**
     * 将指定索引到元素上滤
     *
     * @param index
     */
    private void siftUp(int index) {
        // 获取新添加到元素
        E element = elements[index];
        while (index > 0) {
            // 获取父元素到索引位置
            int pIndex = (index - 1) >> 1;
            // 获取父元素
            E parentElement = elements[pIndex];

            // 如果元素小于等于父元素 则结束交换
            if (compare(element, parentElement) <= 0) {
                return;
            }
            // 将父元素移到当前位置
            elements[index] = parentElement;
            // 新添加元素的位置更新为pIndex
            index = pIndex;
        }
        // 将当前元素放到pIndex的位置
        elements[index] = element;
    }

    private void emptyCheck() {
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException("heap is empty!");
        }
    }

    private void elementNotNullCheck(E e) {
        if (e == null) {
            throw new IllegalArgumentException("element must not be null");
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
    }

}