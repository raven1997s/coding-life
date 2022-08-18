package com.raven.data_structures_and_algorithms.structure.base_15_unionfind;

/**
 * Description:
 * date: 2022/8/17 22:44
 * 并查集
 *
 * @author raven
 */
public abstract class UnionFind {
    protected int[] parents;

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
    public abstract int find(int v);

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
    public abstract void union(int v1, int v2);

    protected void rangeCheck(int v) {
        if (v >= parents.length || v < 0) {
            throw new IllegalArgumentException("v is out of bounds");
        }
    }

    protected void checkParams(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity must be >= 1");
        }
    }
}