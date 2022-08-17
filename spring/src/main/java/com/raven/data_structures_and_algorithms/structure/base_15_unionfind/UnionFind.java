package com.raven.data_structures_and_algorithms.structure.base_15_unionfind;

/**
 * Description:
 * date: 2022/8/17 22:44
 * 并查集
 * quick find
 * @author raven
 */
public class UnionFind {
    private int[] parents;

    public UnionFind(int capacity) {
        checkParams(capacity);
        parents = new int[capacity];
        // 初始化每个元素为单独的集合
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }

    /**
     * 找到v所属的集合（返回根节点）
     *
     * @param v
     * @return
     */
    public int find(int v) {
        rangeCheck(v);
        return parents[v];
    }

    /**
     * 查看俩个元素是否所处于相同的集合
     *
     * @param v1
     * @param v2
     * @return
     */
    public boolean isSame(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /**
     * 合并俩个元素 将v1的父元素指向v2
     *
     * @param v1
     * @param v2
     */
    public void union(int v1, int v2) {
        // 找到俩个元素的父节点
        int parent1 = find(v1);
        int parent2 = find(v2);
        // 父节点一样不需要合并
        if (parent1 == parent2) {
            return;
        }

        // 遍历所有元素，将父节点为p1的元素的父节点也指向p2
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == parent1) {
                parents[i] = parent2;
            }
        }
    }

    private void rangeCheck(int v) {
        if (v >= parents.length || v < 0) {
            throw new IllegalArgumentException("v is out of bounds");
        }
    }

    private void checkParams(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity must be >= 1");
        }
    }
}