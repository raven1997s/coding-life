package com.raven.algorithm.structure.base_09_listset.set;

import com.raven.algorithm.structure.base_09_listset.list.LinkedList;

/**
 * Description:
 * date: 2022/7/20 20:51
 * set集合
 *
 * @author raven
 */
public interface Set<E> {

    /**
     * 返回元素的个数
     *
     * @return
     */
    int size();

    /**
     * 返回集合是否为空
     *
     * @return
     */

    boolean isEmpty();

    /**
     * 查看集合中是否包含指定元素
     *
     * @param element
     * @return
     */
    boolean contains(E element);

    /**
     * 添加元素
     *
     * @param element
     */
    void add(E element);

    /**
     * 删除元素
     *
     * @param element
     */
    void remove(E element);

    /**
     * 清空集合
     */
    void clear();

    /**
     * 遍历
     *
     * @param visitor
     */
    void traversal(Visitor<E> visitor);

    /**
     * 遍历构造器
     *
     * @param <E>
     */
    abstract class Visitor<E> {
        /**
         * 停止遍历的标识
         */
        public boolean stop;

        /**
         * 处理要遍历的元素
         *
         * @param element
         * @return
         */
       public abstract boolean visit(E element);
    }
}