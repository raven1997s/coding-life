package com.raven.data_structures_and_algorithms.structure.base_15_unionfind;

/**
 * Description:
 * date: 2022/8/18 21:51
 * quick union
 * 基于高度进行优化
 * 将高度矮的根节点嫁接到高度高的根节点下
 * 基于高度优化下再进行路径分离优化
 * @author raven
 */
public class QuickUnionUnionFind_Rank_PathSpilting extends QuickUnionUnionFind_Rank {

    public QuickUnionUnionFind_Rank_PathSpilting(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        // 如果v的值不等于v根节点的值 将v父节点的值给v
        while (v != parents[v]) {
            // 取出v的父节点
            int p = parents[v];
            // 让v指向v的祖父节点(v的父节点改为v的祖父节点)
            parents[v] = parents[parents[v]];
            v = p;
        }
        return v;
    }

}