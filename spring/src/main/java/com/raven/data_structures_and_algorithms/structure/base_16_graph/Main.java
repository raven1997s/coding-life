package com.raven.data_structures_and_algorithms.structure.base_16_graph;

import org.apache.commons.lang3.StringUtils;
import sun.java2d.pipe.SpanIterator;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Description:
 * date: 2022/8/21 11:13
 *
 * @author raven
 */
public class Main {

    static Graph.WeightManger<Double> weightManger = new Graph.WeightManger<Double>() {
        @Override
        public int compare(Double w1, Double w2) {
            return w1.compareTo(w2);
        }

        @Override
        public Double add(Double w1, Double w2) {
            return w1 + w2;
        }
    };

    public static void main(String[] args) {
        //bsf("V1");
        //testBsf();
        //dfs();
        //topoTest();
        //testMst();
        testSp();
    }

    public static void testSp() {
        Graph<Object, Double> graph = undirectedGraph(Data.SP);
        Map<Object, Double> shortestPath = graph.shortestPath("A");
        shortestPath.forEach((key,value) -> {
            System.out.println(key + "__" + value);
        });
    }

    public static void testMst() {
        Graph<Object, Double> graph = undirectedGraph(Data.MST_01);
        Set<Graph.EdgeInfo<Object, Double>> infos = graph.mst();
        for (Graph.EdgeInfo<Object, Double> info : infos) {
            System.out.println(info);
        }
    }

    public static void topoTest() {
        Graph<Object, Double> graph = directedGraph(Data.TOPO);
        graph.bfs(3, o -> {
            System.out.println(o);
            return false;
        });
        System.out.println("=====");
        graph.topologicalSort().forEach(System.out::println);
    }

    public static void dfs() {
        Graph<Object, Double> graph = directedGraph(Data.DFS_02);
        graph.dfs("a", o -> {
            System.out.println(o);
            return o == "e";
        });

        System.out.println("=:-:-:-:-:-:-:-:-");
        ListGraph<Object, Double> graph2 = (ListGraph<Object, Double>) directedGraph(Data.DFS_02);
        graph2.dfs2("a");
    }

    public static void testBsf() {
        Graph<Object, Double> graph = directedGraph(Data.BFS_02);
        graph.bfs(0, o -> {
            System.out.println(o);
            return Integer.parseInt(o.toString()) == 4;
        });
    }

    public static void bsf(String v) {
        ListGraph<String, Integer> graph = buildGraph();
        graph.bfs1(v);
    }

    public static ListGraph<String, Integer> buildGraph() {
        ListGraph<String, Integer> graph = new ListGraph<>();
        graph.addEdge("V0", "V4", 6);
        graph.addEdge("V1", "V0", 9);
        graph.addEdge("V1", "V2", 3);
        graph.addEdge("V2", "V0", 2);
        graph.addEdge("V2", "V3", 5);
        graph.addEdge("V3", "V4", 1);
        return graph;
    }

    public static void baseTest() {
        ListGraph<String, Integer> graph = buildGraph();
        graph.removeVertex("V0");
        graph.print();
    }

    /**
     * 有向图
     */
    private static Graph<Object, Double> directedGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>(weightManger);
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
            }
        }
        return graph;
    }

    /**
     * 无向图
     *
     * @param data
     * @return
     */
    private static Graph<Object, Double> undirectedGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>(weightManger);
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
                graph.addEdge(edge[1], edge[0]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
                graph.addEdge(edge[1], edge[0], weight);
            }
        }
        return graph;
    }

}