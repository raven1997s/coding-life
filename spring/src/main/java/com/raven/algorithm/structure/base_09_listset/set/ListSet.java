package com.raven.algorithm.structure.base_09_listset.set;

import com.raven.algorithm.structure.base_09_listset.list.LinkedList;
import com.raven.algorithm.structure.base_09_listset.list.List;

/**
 * Description:
 * date: 2022/7/20 21:01
 * 用链表实现Set集合
 *
 * @author raven
 */
public class ListSet<E> implements Set<E> {
    private List<E> list = new LinkedList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(E element) {
        return list.contains(element);
    }

    @Override
    public void add(E element) {
        // Set储存元素 如果存在就覆盖，不存在就添加
        if (contains(element)) {
            int index = list.indexOf(element);
            if (index != List.NOT_FOUND_ELEMENT) {
                list.set(index, element);
            }
        } else {
            list.add(element);
        }
    }

    @Override
    public void remove(E element) {
        int index = list.indexOf(element);
        if (index != List.NOT_FOUND_ELEMENT){
            list.remove(index);
        }
    }

    @Override
    public void clear() {
        list.clear();
    }

    /**
     * 遍历器
     * @param visitor
     */
    @Override
    public void traversal(Visitor<E> visitor) {
        if (visitor == null){
            return;
        }

        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (visitor.visit(list.get(i))){
                return;
            }
        }
    }
}