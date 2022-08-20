package com.raven.data_structures_and_algorithms.structure.base_15_unionfind;

/**
 * Description:
 * date: 2022/8/18 21:51
 * quick union
 * 基于高度进行优化
 * 将高度矮的根节点嫁接到高度高的根节点下
 * 基于高度优化下再进行路径压缩
 * @author raven
 */
public class QuickUnionUnionFind_Rank_PathCompression extends QuickUnionUnionFind_Rank {

    public QuickUnionUnionFind_Rank_PathCompression(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        // 如果v的父节点不是根节点 递归获取父节点直到根节点 并把路径上的每一个节点的父节点都修改为根节点
        if (parents[v] != v){
            parents[v] = find(parents[v]);
        }
        return parents[v];
    }

}