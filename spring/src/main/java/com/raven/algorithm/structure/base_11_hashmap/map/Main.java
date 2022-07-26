package com.raven.algorithm.structure.base_11_hashmap.map;

import com.raven.algorithm.structure.base_11_hashmap.entity.Key;
import com.raven.algorithm.structure.base_11_hashmap.entity.Person;
import com.raven.algorithm.structure.utils.Asserts;

/**
 * Description:
 * date: 2022/7/25 22:04
 *
 * @author raven
 */
public class Main {
    public static void main(String[] args) {
        testKey();
    }

    static void testKey() {
        HashMap<Object, Integer> map = new HashMap<>();
        for (int i = 1; i <= 19; i++) {
            map.put(new Key(i), i);
        }
        map.put(new Key(100), 100);
        Asserts.test(map.size() == 20);
        Asserts.test(map.get(new Key(100)) == 100);
        Asserts.test(map.containsKey(new Key(100)));
        Asserts.test(map.containsValue(100));
        map.print();
    }

    static void baseTest() {

        Person p1 = new Person(18, 175.2f, "小熊");
        Person p2 = new Person(18, 175.2f, "小熊");
        Map<Object, Integer> map = new HashMap<>();
        map.put(p1, 11);
        map.put(p2, 22);
        System.out.println(map.size());

        map.put("xiao", 33);
        map.put("gao", 44);
        System.out.println("33:" + map.get("xiao"));
        System.out.println("true" + map.containsKey("xiao"));
        System.out.println("true" + map.containsValue(44));
        map.traversal(new Map.Visitor<Object, Integer>() {
            @Override
            public boolean visit(Object key, Integer value) {
                System.out.println(key.toString() + "_" + value);
                return false;
            }
        });
        System.out.println("44" + map.remove("gao"));
        System.out.println("2" + map.size());
        map.clear();
        System.out.println("0" + map.size());
    }
}