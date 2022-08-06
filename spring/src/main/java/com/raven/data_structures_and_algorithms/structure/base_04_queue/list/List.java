package com.raven.data_structures_and_algorithms.structure.base_04_queue.list;

/**
 * Description:
 * date: 2022/6/8 21:54
 * 抽象公共的接口
 *
 * @author raven
 */
public interface List<E> {
    static final int NOT_FOUND_ELEMENT = -1;

    /**
     * @return 数组的长度
     */
    int size();

    /**
     * 在末尾添加一个元素
     *
     * @param element
     */
    void add(E element);

    /**
     * 在index的位置插入一个元素
     *
     * @param element
     * @param index
     */
    void add(E element, int index);


    /**
     * 删除指定索引位置的元素
     * @param index
     * @return
     */
    E remove(int index);

    /**
     * 判断列表中是否包含指定元素
     *
     * @param element
     * @return
     */
    boolean contains(E element);

    /**
     * 获取指定位置的元素
     *
     * @param index
     * @return
     */
    E get(int index);

    /**
     * 设置指定位置的元素为element
     *
     * @param index   替换index位置的元素
     * @param element 要替换的元素
     * @return 被替换的元素值
     */
    E set(int index, E element);

    /**
     * 清空列表
     */
    void clear();

    /**
     * 判断列表是否为空
     *
     * @return
     */
    boolean isEmpty();

    /**
     * 返回指定element的位置
     *
     * @param element
     * @return
     */
    int indexOf(E element);
}