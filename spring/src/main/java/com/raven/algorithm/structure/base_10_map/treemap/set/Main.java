package com.raven.algorithm.structure.base_10_map.treemap.set;

/**
 * Description:
 * date: 2022/7/23 10:41
 *
 * @author raven
 */
public class Main {

    public static void main(String[] args) {
        testTreeSet();
    }

    static void testTreeSet() {
        Set<String> set = new TreeSet<>();
        set.add("c");
        set.add("b");
        set.add("a");
        set.add("a");
        System.out.println(set.size());
        set.traversal(new Set.Visitor<String>() {
            @Override
            public boolean visit(String element) {
                System.out.println(element);
                return false;
            }
        });
    }
}