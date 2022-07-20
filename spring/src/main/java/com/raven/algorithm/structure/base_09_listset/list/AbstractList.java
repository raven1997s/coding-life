package com.raven.algorithm.structure.base_09_listset.list;


/**
 * Description:
 * date: 2022/6/8 21:53
 * 抽象集合的公共代码
 * @author raven
 */
public abstract class AbstractList<E>  implements List<E> {
    protected int size;
    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(E element) {
        add(element, size);
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != NOT_FOUND_ELEMENT;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    protected void outOfBounds(int index) {
        throw new ArrayIndexOutOfBoundsException("index :" + index + ", size: " + size);
    }

    protected void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }

    protected void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }
}