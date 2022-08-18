package com.raven.data_structures_and_algorithms.structure.base_15_unionfind;

/**
 * Description:
 * date: 2022/8/18 21:51
 * quick union
 *
 * @author raven
 */
public class QuickUnionUnionFind extends UnionFind {
    public QuickUnionUnionFind(int capacity) {
        super(capacity);
    }

    /**
     * 通过parent链条不断地向上找，直到找到根节点
     *
     * @param v
     * @return
     */
    @Override
    public int find(int v) {
        rangeCheck(v);
        // 如果v的值不等于v根节点的值 将v父节点的值给v
        while (v != parents[v]) {
            v = parents[v];
        }
        return v;
    }

    /**
     * 将v1的根节点嫁接到v2的根节点上
     *
     * @param v1
     * @param v2
     */
    @Override
    public void union(int v1, int v2) {
        // 找到v1 v2 的根节点
        int p1 = find(v1);
        int p2 = find(v2);

        if (p1 == p2) {
            return;
        }

        parents[p1] = p2;
    }
}