package com.raven.data_structures_and_algorithms.structure.base_15_unionfind;

/**
 * Description:
 * date: 2022/8/18 21:51
 * quick union
 * 基于高度进行优化
 * 将高度矮的根节点嫁接到高度高的根节点下
 *
 * @author raven
 */
public class QuickUnionUnionFind_Rank extends QuickUnionUnionFind {
    private int[] ranks;

    public QuickUnionUnionFind_Rank(int capacity) {
        super(capacity);
        // 记录每个根节点的高度
        ranks = new int[capacity];
        for (int i = 0; i < ranks.length; i++) {
            ranks[i] = 1;
        }
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

        // 根节点p1 的高度高于 p2的高度 把p2嫁接到p1
        if (ranks[p1] > ranks[p2]) {
            parents[p2] = p1;
        } else if (ranks[p1] < ranks[p2]) {
            parents[p1] = p2;
        } else {
            // 高度相等，将任意一棵树嫁接到另一个树上
            parents[p2] = p1;
            // p2嫁接到p1  p1高度+1
            ranks[p1]++;
        }
    }
}