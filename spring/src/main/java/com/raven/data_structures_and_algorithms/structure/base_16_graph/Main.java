package com.raven.data_structures_and_algorithms.structure.base_16_graph;

import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Objects;

/**
 * Description:
 * date: 2022/8/21 11:13
 *
 * @author raven
 */
public class Main {
    public static void main(String[] args) {

        ListGraph<String, Integer> graph = new ListGraph<>();
        graph.addEdge("V0", "V4", 6);
        graph.addEdge("V1", "V0", 9);
        graph.addEdge("V1", "V2", 3);
        graph.addEdge("V2", "V0", 2);
        graph.addEdge("V2", "V3", 5);
        graph.addEdge("V3", "V4", 1);

        graph.print();
    }

}