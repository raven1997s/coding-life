package com.raven.data_structures_and_algorithms.structure.base_15_unionfind;

/**
 * Description:
 * date: 2022/8/18 21:45
 * quick find
 *
 * @author raven
 */
public class QuickFindUnionFind extends UnionFind {
    public QuickFindUnionFind(int capacity) {
        super(capacity);
    }

    /**
     * 找到v所属的集合（返回根节点）
     * <p>
     * 父节点就是根节点
     *
     * @param v
     * @return
     */
    @Override
    public int find(int v) {
        rangeCheck(v);
        return parents[v];
    }

    /**
     * 将v1所在集合的所有元素都嫁接到v2的父节点上
     *
     * @param v1
     * @param v2
     */
    @Override
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
}