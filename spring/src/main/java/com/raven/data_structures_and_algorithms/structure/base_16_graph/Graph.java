package com.raven.data_structures_and_algorithms.structure.base_16_graph;


import java.util.List;
import java.util.Set;

/**
 * Description:
 * date: 2022/8/21 11:13
 * 图抽象接口
 *
 * @author raven
 */
public interface Graph<V, E> {

    /**
     * 返回图的边的数量
     *
     * @return 边的数量
     */
    int edgesSize();

    /**
     * 返回图的顶点数量
     *
     * @return 顶点数量
     */
    int verticesSize();

    /**
     * 添加一个顶点
     *
     * @param v 顶点元素值
     */
    void addVertex(V v);

    /**
     * 添加一条边 并指定从哪个顶点到哪个顶点
     *
     * @param from 从from顶点元素值出发
     * @param to   到to顶点元素值
     */
    void addEdge(V from, V to);

    /**
     * 添加一条边 并指定从哪个顶点到哪个顶点 以及权值E
     *
     * @param from   从from顶点元素值出发
     * @param to     到to顶点元素值
     * @param weight 权值
     */
    void addEdge(V from, V to, E weight);

    /**
     * 删除指定元素到顶点
     *
     * @param v 元素值
     */
    void removeVertex(V v);

    /**
     * 删除指定边
     *
     * @param from 出发顶点元素值
     * @param to   到达顶点元素值
     */
    void removeEdge(V from, V to);

    /**
     * 从指定元素出发进行广度优先搜索遍历
     *
     * @param begin
     */
    void bfs(V begin, VertexVisitor<V> visitor);

    /**
     * 从指定元素出发进行深度优先搜索遍历
     *
     * @param begin
     */
    void dfs(V begin, VertexVisitor<V> visitor);

    /**
     * 拓扑排序
     *
     * @return 返回排序好后的结果
     */
    List<V> topologicalSort();

    interface VertexVisitor<V> {
        boolean visitor(V v);
    }

    // 最小生成树 （minimum spanning tree）
    // 也称为最小权重生成树(Minimum Weight Spanning Tree)、最小支撑树
    // 是所有生成树中，总权值最小的那棵  适用于有权的连通图（无向）

    // 假设G=(V，E)是有权的连通图（无向）)，A是G中最小生成树的边集
    // 算法从S={uo}(uo EV)，A={}开始，重复执行下述操作，直到S=V为止
    // 找到切分 C=(S，V-S) 的最小横切边（uo，Vo）并入集合 A，同时将 Vo并入集合S

    Set<EdgeInfo<V, E>> mst();

    class EdgeInfo<V, E> {
        V from;
        V to;
        E eight;

        public EdgeInfo(V from, V to, E eight) {
            this.from = from;
            this.to = to;
            this.eight = eight;
        }
    }
}