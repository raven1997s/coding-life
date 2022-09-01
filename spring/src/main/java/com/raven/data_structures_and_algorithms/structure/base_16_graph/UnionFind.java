package com.raven.data_structures_and_algorithms.structure.base_16_graph;

import com.raven.data_structures_and_algorithms.structure.base_10_map.hashmap.map.HashMap;
import com.raven.data_structures_and_algorithms.structure.base_10_map.hashmap.map.Map;

import java.util.Objects;

/**
 * Description:
 * date: 2022/8/20 20:10
 * 范型通用的并查集
 *
 * @author raven
 */
public class UnionFind<V> {

    /**
     * 存储元素和节点的一一映射关系
     */
    private final Map<V, Node<V>> nodes = new HashMap<>();

    /**
     * 初始化元素到并查集中
     *
     * @param v
     */
    public void markSet(V v) {
        if (nodes.containsKey(v)) {
            return;
        }
        nodes.put(v, new Node<>(v));
    }

    /**
     * 合并v1 和v2
     *
     * @param v1
     * @param v2
     */
    public void union(V v1, V v2) {
        Node<V> p1 = findNode(v1);
        Node<V> p2 = findNode(v2);
        // 如果v1的根节点为null 或者p2的根节点为null 则不需要合并
        if (p1 == null || p2 == null) {
            return;
        }

        // 根节点的值相同不需要合并
        if (Objects.equals(p1.value, p2.value)) {
            return;
        }

        // p1的高度高，就把p2合并到p1下
        if (p1.rank > p2.rank) {
            p2.parent = p1;
        } else if (p1.rank < p2.rank) {
            p1.parent = p2;
        } else {
            // p1 p2高度一致
            p2.parent = p1;
            // p2合并到p1下 p1高度增加
            p1.rank++;
        }
    }

    /**
     * 返回元素v的根节点元素v
     *
     * @param v
     * @return
     */
    public V find(V v) {
        Node<V> node = findNode(v);
        return node == null ? null : node.value;
    }

    /**
     * 判断v1和v2是否属于同一链路上
     *
     * @param v1
     * @param v2
     * @return
     */
    public boolean isSame(V v1, V v2) {
        return Objects.equals(find(v1), find(v2));
    }

    /**
     * 找到v元素的根节点
     *
     * @param v
     * @return
     */
    private Node<V> findNode(V v) {
        Node<V> node = nodes.get(v);
        if (node == null) {
            return null;
        }
        // 如果node的值不等于父节点的值 把node的父节点指向node的祖父节点
        while (!Objects.equals(node.value, node.parent.value)) {
            node.parent = node.parent.parent;
            // 把父节点赋值给node
            node = node.parent;
        }
        return node;
    }

    /**
     * 通过node存储元素
     *
     * @param <V>
     */
    private static class Node<V> {
        // 根节点的高度 初始化时为1
        private int rank = 1;
        // 元素值
        private V value;
        // 父节点 初始化时父节点时自己
        private Node<V> parent = this;

        public Node(V value) {
            this.value = value;
        }
    }
}