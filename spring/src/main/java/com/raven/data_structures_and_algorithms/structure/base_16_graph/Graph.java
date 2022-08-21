package com.raven.data_structures_and_algorithms.structure.base_16_graph;

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
     * @param begin
     */
    void bfs(V begin);

    /**
     * 从指定元素出发进行深度优先搜索遍历
     * @param begin
     */
    void dfs(V begin);
}