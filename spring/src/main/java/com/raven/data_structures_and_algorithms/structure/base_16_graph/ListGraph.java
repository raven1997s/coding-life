package com.raven.data_structures_and_algorithms.structure.base_16_graph;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Description:
 * date: 2022/8/21 11:13
 * 用集合实现图
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
    }

    @Override
    public void removeVertex(V v) {

    }

    @Override
    public void removeEdge(V from, V to) {

    }


    public void print() {
        edges.forEach(System.out::println);

        vertices.forEach((v,vertex) -> {
            System.out.println(v);
            System.out.println("======="+v+"=======outEdges=======");
            System.out.println(vertex.outEdges);
            System.out.println("======="+v+"=======inEdges=======");
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