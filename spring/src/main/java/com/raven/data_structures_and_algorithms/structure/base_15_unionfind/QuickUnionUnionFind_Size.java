package com.raven.data_structures_and_algorithms.structure.base_15_unionfind;

/**
 * Description:
 * date: 2022/8/18 21:51
 * quick union
 * 基于size进行优化
 * 用数组记录每一个根节点下元素的个数
 * 将元素少的根节点嫁接到元素多的根节点下
 * @author raven
 */
public class QuickUnionUnionFind_Size extends QuickUnionUnionFind {
    private int[] sizes;

    public QuickUnionUnionFind_Size(int capacity) {
        super(capacity);
        // 定义数组记录每一个根节点的元素个数
        sizes = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            sizes[i] = 1;
        }
    }

    /**
     * 将元素少的根节点嫁接到元素多的根节点下
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

        // 如果p1位置元素多，就把p2的根节点嫁接到p1上
        if (sizes[p1] > sizes[p2]) {
            parents[p2] = p1;
            sizes[p1] += sizes[p2];
        } else {
            // p2位置元素多，就把p1的根节点嫁接到p2上
            parents[p1] = p2;
            sizes[p2] += sizes[p1];
        }
    }
}