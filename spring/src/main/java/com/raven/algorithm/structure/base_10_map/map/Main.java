package com.raven.algorithm.structure.base_10_map.map;

/**
 * Description:
 * date: 2022/7/21 17:05
 *
 * @author raven
 */
public class Main {
    public static void main(String[] args) {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("张三", "15");
        map.put("王武", "25");
        map.put("张武", "16");
        System.out.println(map.get("张三"));
        map.put("张三", "16");
        System.out.println(map.get("张三"));
        map.put("李四", "15");
        System.out.println(map.size());
        System.out.println(map.containsKey("李四"));
        System.out.println(map.remove("张三"));
        System.out.println(map.size());
        System.out.println(map);

        map.traversal(new Map.Visitor<String, String>() {
            @Override
            public boolean visit(String key, String value) {
                System.out.println(key + ":" + value);
                return false;
            }
        });
    }
}