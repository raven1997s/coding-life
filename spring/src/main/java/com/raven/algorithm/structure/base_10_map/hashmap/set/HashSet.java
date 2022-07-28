package com.raven.algorithm.structure.base_10_map.hashmap.set;


import com.raven.algorithm.structure.base_10_map.hashmap.map.HashMap;
import com.raven.algorithm.structure.base_10_map.hashmap.map.Map;

/**
 * Description:
 * date: 2022/7/28 10:30
 *
 * @author raven
 */
public class HashSet<E> implements Set<E> {
    private HashMap<E, Object> map = new HashMap<>();

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
        if (visitor == null) {
            return;
        }
        map.traversal(new Map.Visitor<E, Object>() {
            @Override
            public boolean visit(E key, Object value) {
                return visitor.visit(key);
            }
        });
    }
}