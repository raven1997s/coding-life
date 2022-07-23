package com.raven.algorithm.structure.base_10_map.set;

import com.raven.algorithm.structure.base_10_map.map.Map;
import com.raven.algorithm.structure.base_10_map.map.TreeMap;

/**
 * Description:
 * date: 2022/7/23 10:37
 * 用TreeMap实现TreeSet
 *
 * @author raven
 */
public class TreeSet<E> implements Set<E> {

    private TreeMap<E, Object> map = new TreeMap<>();

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(E element) {
        return map.containsKey(element);
    }

    @Override
    public void add(E element) {
        map.put(element, null);
    }

    @Override
    public void remove(E element) {
        map.remove(element);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        map.traversal(new Map.Visitor<E, Object>() {
            @Override
            public boolean visit(E key, Object value) {
                return visitor.visit(key);
            }
        });
    }
}