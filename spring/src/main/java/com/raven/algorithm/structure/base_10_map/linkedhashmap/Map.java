package com.raven.algorithm.structure.base_10_map.linkedhashmap;

/**
 * Description:
 * date: 2022/7/21 09:44
 *
 * @author raven
 */
public interface Map<K, V> {

    int size();

    boolean isEmpty();

    void clear();

    /**
     * 添加数据，如果key已经存在，返回被替换的value的值
     *
     * @param key
     * @param value
     * @return
     */
    V put(K key, V value);

    V get(K key);

    V remove(K key);

    boolean containsKey(K key);

    boolean containsValue(V value);

    void traversal(Visitor<K, V> visitor);

    abstract class Visitor<K, V> {
        boolean stop;

        public abstract boolean visit(K key, V value);
    }


}