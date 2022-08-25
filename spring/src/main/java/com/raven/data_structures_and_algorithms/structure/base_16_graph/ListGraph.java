package com.raven.data_structures_and_algorithms.structure.base_16_graph;


import com.google.common.collect.Lists;

import java.util.*;

/**
 * Description:
 * date: 2022/8/21 11:13
 * 用集合实现图
 * 邻接表
 *
 * @author raven
 */
@SuppressWarnings("unchecked")
public class ListGraph<V, E> implements Graph<V, E> {
    // 存储所有的边
    private Set<Edge<V, E>> edges = new HashSet<>();
    // 存储V元素和顶点的映射
    private HashMap<V, Vertex<V, E>> vertices = new HashMap<>();

    @Override
    public int edgesSize() {
        return edges.size();
    }

    @Override
    public int verticesSize() {
        return vertices.size();
    }

    /**
     * 添加一个顶点
     *
     * @param v 顶点元素值
     */
    @Override
    public void addVertex(V v) {
        if (vertices.containsKey(v)) {
            return;
        }
        vertices.put(v, new Vertex<>(v));
    }

    /**
     * 添加一条边
     *
     * @param from 从from顶点出发
     * @param to   到to顶点
     */
    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    /**
     * @param from   从from顶点出发
     * @param to     到to顶点
     * @param weight 权值
     */
    @Override
    public void addEdge(V from, V to, E weight) {
        // 添加一条边，需要先判断顶点是否存在，顶点不存在创建顶点
        Vertex<V, E> formVertex = vertices.get(from);
        if (formVertex == null) {
            formVertex = new Vertex<>(from);
            vertices.put(from, formVertex);
        }

        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) {
            toVertex = new Vertex<>(to);
            vertices.put(to, toVertex);
        }

        // 添加边 判断边是否存在，边存在用新的边覆盖旧的边
        // 判断到达顶点的 从其他顶点到该顶点的边集合中是否包含要添加的边
        Edge<V, E> edge = new Edge<>(formVertex, toVertex, weight);

        if (toVertex.inEdges.remove(edge)) {
            formVertex.outEdges.remove(edge);
            edges.remove(edge);
        }

        formVertex.outEdges.add(edge);
        toVertex.inEdges.add(edge);
        edges.add(edge);
    }

    /**
     * 删除顶点
     *
     * @param v 元素值
     */
    @Override
    public void removeVertex(V v) {
        // 直接尝试删除顶点 删除成功会返回顶点对象
        Vertex<V, E> vertex = vertices.remove(v);
        // 顶点不存在
        if (vertex == null) {
            return;
        }

        // 删除该顶点出发的边
        Iterator<Edge<V, E>> outIterator = vertex.outEdges.iterator();
        while (outIterator.hasNext()) {
            Edge<V, E> edge = outIterator.next();
            // 获取edge边的to到达顶点。从到达顶点的inEdges中删除边
            edge.to.inEdges.remove(edge);
            // 删除出发顶点的边
            outIterator.remove();
            // 从边的集合中删除
            edges.remove(edge);
        }

        // 删除到达该顶点的边
        Iterator<Edge<V, E>> inIterator = vertex.inEdges.iterator();
        while (inIterator.hasNext()) {
            Edge<V, E> edge = inIterator.next();
            // 获取edge的出发顶点， 出发顶点的outEdges中删除该边
            edge.from.outEdges.remove(edge);
            // 删除edge到达顶点的边
            inIterator.remove();
            // 从边的集合中删除
            edges.remove(edge);
        }

    }

    /**
     * 删除边
     *
     * @param from 出发顶点元素值
     * @param to   到达顶点元素值
     */
    @Override
    public void removeEdge(V from, V to) {
        // 获取出发顶点
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            return;
        }
        // 获取到达顶点
        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) {
            return;
        }

        // 根据顶点构建边
        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        // 如果出发顶点删除边成功 则在到达顶点也删除边
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
    }

    @Override
    public void bfs(V begin, VertexVisitor<V> visitor) {
        if (visitor == null) {
            return;
        }
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) {
            return;
        }

        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        Deque<Vertex<V, E>> queue = new LinkedList<>();
        queue.offer(beginVertex);
        visitedVertices.add(beginVertex);
        while (!queue.isEmpty()) {
            Vertex<V, E> vertex = queue.poll();
            if (visitor.visitor(vertex.value)) {
                return;
            }
            // 获取veVertex 出发的所有边的到达顶点放入队列
            vertex.outEdges.forEach(edge -> {
                if (visitedVertices.contains(edge.to)) {
                    return;
                }
                queue.offer(edge.to);
                visitedVertices.add(edge.to);
            });
        }
    }

    @Override
    public void dfs(V begin, VertexVisitor<V> visitor) {
        if (visitor == null) {
            return;
        }

        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) {
            return;
        }
        // 记录已经遍历过的顶点
        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        // 通过栈实现模拟递归
        Stack<Vertex<V, E>> stack = new Stack<>();
        if (visitor.visitor(beginVertex.value)) {
            return;
        }
        visitedVertices.add(beginVertex);
        stack.push(beginVertex);

        while (!stack.isEmpty()) {
            // 获取栈顶顶点
            Vertex<V, E> vertex = stack.pop();
            // 获取所有从该顶点出发的边 选择一条边进行访问
            for (Edge<V, E> edge : vertex.outEdges) {
                // 如果访问过就换下一条边
                if (visitedVertices.contains(edge.to)) {
                    continue;
                }
                // 访问并记录
                if (visitor.visitor(edge.to.value)) {
                    return;
                }
                visitedVertices.add(edge.to);
                stack.push(edge.from);
                stack.push(edge.to);
                break;
            }
        }
    }

    @Override
    public List<V> topologicalSort() {
        // 定义结果集封装排序结果
        List<V> result = new ArrayList<>();
        // 定义队列记录接下来需要排序的 入度为0的顶点
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        // 定义map存储顶点对应的入度值 k 顶点 v 入度值
        Map<Vertex<V, E>, Integer> inMap = new HashMap<>();
        vertices.forEach((v, vertex) -> {
            int in = vertex.inEdges.size();
            if (in == 0) {
                queue.offer(vertex);
            } else {
                inMap.put(vertex, in);
            }
        });

        // 取出队列中入度为0的顶点 放入结果集中
        while (!queue.isEmpty()) {
            Vertex<V, E> vertex = queue.poll();
            result.add(vertex.value);
            // 遍历度为0顶点的出发边列表
            for (Edge<V, E> edge : vertex.outEdges) {
                // 从map中取出到达顶点的入度值
                Vertex<V, E> toVertex = edge.to;
                int in = inMap.get(toVertex) - 1;
                // 如果入度值修改为0 则放入队列 否则更新map中顶点的入度值
                if (in == 0) {
                    queue.offer(toVertex);
                } else {
                    inMap.put(toVertex, in);
                }
            }
        }
        return result;
    }

    /**
     * 从指定元素出发进行广度优先搜索遍历
     *
     * @param begin
     */
    public void bfs1(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) {
            return;
        }

        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        Deque<Vertex<V, E>> queue = new LinkedList<>();
        queue.offer(beginVertex);
        visitedVertices.add(beginVertex);
        while (!queue.isEmpty()) {
            Vertex<V, E> vertex = queue.poll();
            System.out.println(vertex.value);
            // 获取veVertex 出发的所有边的到达顶点放入队列
            vertex.outEdges.forEach(edge -> {
                if (visitedVertices.contains(edge.to)) {
                    return;
                }
                queue.offer(edge.to);
                visitedVertices.add(edge.to);
            });
        }
    }

    /**
     * 从指定元素出发进行深度优先搜索遍历
     * 迭代实现方案
     *
     * @param begin
     */
    public void dfs1(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) {
            return;
        }
        // 记录已经遍历过的顶点
        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        // 通过栈实现模拟递归
        Stack<Vertex<V, E>> stack = new Stack<>();
        System.out.println(beginVertex.value);
        visitedVertices.add(beginVertex);
        stack.push(beginVertex);

        while (!stack.isEmpty()) {
            // 获取栈顶顶点
            Vertex<V, E> vertex = stack.pop();
            // 获取所有从该顶点出发的边 选择一条边进行访问
            for (Edge<V, E> edge : vertex.outEdges) {
                // 如果访问过就换下一条边
                if (visitedVertices.contains(edge.to)) {
                    continue;
                }
                // 访问并记录
                System.out.println(edge.to.value);
                visitedVertices.add(edge.to);
                stack.push(edge.from);
                stack.push(edge.to);
                break;
            }
        }
    }

    /**
     * 深度优先搜索图 递归实现方案
     *
     * @param begin
     */
    public void dfs2(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) {
            return;
        }
        // 记录已经遍历过的顶点
        dfs2(beginVertex, new HashSet<>());
    }

    /**
     * 递归深度优先遍历访问顶点元素
     *
     * @param vertex          顶点
     * @param visitedVertices 已经访问过的顶点
     */
    private void dfs2(Vertex<V, E> vertex, Set<Vertex<V, E>> visitedVertices) {
        // 记录已经访问过的顶点
        visitedVertices.add(vertex);
        System.out.println(vertex.value);
        for (Edge<V, E> edge : vertex.outEdges) {
            if (visitedVertices.contains(edge.to)) {
                continue;
            }
            dfs2(edge.to, visitedVertices);
        }
    }

    public void print() {
        System.out.println("[边]");
        edges.forEach(System.out::println);

        System.out.println("顶点");
        vertices.forEach((v, vertex) -> {
            System.out.println(v);
            System.out.println("=======" + v + "=======outEdges=======");
            System.out.println(vertex.outEdges);
            System.out.println("=======" + v + "=======inEdges=======");
            System.out.println(vertex.inEdges);
        });
    }

    /**
     * 顶点的结构
     *
     * @param <V> 元素值
     * @param <E> 权值
     */
    private static class Vertex<V, E> {
        // 顶点元素值
        V value;
        // 从该顶点出发的边的集合
        Set<Edge<V, E>> outEdges = new HashSet<>();
        // 从其他顶点到达该顶点边的集合
        Set<Edge<V, E>> inEdges = new HashSet<>();

        public Vertex(V vlaue) {
            this.value = vlaue;
        }

        /**
         * 重写equals 如果顶点元素值相等则认为是相同顶点
         *
         * @param o
         * @return
         */
        @Override
        public boolean equals(Object o) {
            Vertex<V, E> vertex = (Vertex<V, E>) o;
            return Objects.equals(value, vertex.value);
        }

        @Override
        public int hashCode() {
            return value == null ? 0 : value.hashCode();
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "value=" + value +
                    '}';
        }
    }

    /**
     * 边的结构
     *
     * @param <V> 元素值
     * @param <E> 权值
     */
    private static class Edge<V, E> {
        // 出发顶点
        Vertex<V, E> from;
        // 到达顶点
        Vertex<V, E> to;
        // 权值
        E weight;

        @Override
        public int hashCode() {
            return from.hashCode() * 31 + to.hashCode();
        }

        public Edge(Vertex<V, E> from, Vertex<V, E> to) {
            this.from = from;
            this.to = to;
        }

        public Edge(Vertex<V, E> from, Vertex<V, E> to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        /**
         * 重equals方法 如果from和to相同就认为是相同顶点
         *
         * @param o
         * @return
         */
        @Override
        public boolean equals(Object o) {
            Edge<V, E> edge = (Edge<V, E>) o;
            return Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }
}