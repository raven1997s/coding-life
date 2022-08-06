package com.raven.data_structures_and_algorithms.structure.base_10_map.linkedhashmap;

import com.raven.data_structures_and_algorithms.structure.base_10_map.hashmap.entity.Key;
import com.raven.data_structures_and_algorithms.structure.base_10_map.hashmap.entity.Person;
import com.raven.data_structures_and_algorithms.structure.base_10_map.hashmap.entity.SubKey1;
import com.raven.data_structures_and_algorithms.structure.base_10_map.hashmap.entity.SubKey2;
import com.raven.utils.Asserts;
import com.raven.utils.FileInfo;
import com.raven.utils.Files;
import com.raven.utils.Times;

/**
 * Description:
 * date: 2022/7/25 22:04
 *
 * @author raven
 */
public class Main {
    public static void main(String[] args) {
        //testKey();
        keyEqualsTest();
        test2(new LinkedHashMap<>());
        test3(new LinkedHashMap<>());
        test4(new LinkedHashMap<>());
        test5(new LinkedHashMap<>());
        test1();
    }

    static void test1() {
        String filepath = "/Users/raven/Documents/devlop/mall-master";
        FileInfo fileInfo = Files.read(filepath, null);
        String[] words = fileInfo.words();

        System.out.println("总行数：" + fileInfo.getLines());
        System.out.println("单词总数：" + words.length);
        System.out.println("-------------------------------------");

        //test1Map(new TreeMap<>(), words);
        //test1Map(new HashMap<>(), words);
        test1Map(new LinkedHashMap<>(), words);
    }


    static void test1Map(Map<String, Integer> map, String[] words) {
        Times.test(map.getClass().getName(), () -> {
            for (String word : words) {
                Integer count = map.get(word);
                count = count == null ? 0 : count;
                map.put(word, count + 1);
            }
            System.out.println(map.size()); // 12884

            int count = 0;
            for (String word : words) {
                Integer i = map.get(word);
                count += i == null ? 0 : i;
                map.remove(word);
            }
            Asserts.test(count == words.length);
            Asserts.test(map.size() == 0);
        });
    }
    static void test2(HashMap<Object, Integer> map) {
        for (int i = 1; i <= 20; i++) {
            map.put(new Key(i), i);
        }
        for (int i = 5; i <= 7; i++) {
            map.put(new Key(i), i + 5);
        }
        Asserts.test(map.size() == 20);
        Asserts.test(map.get(new Key(4)) == 4);
        Asserts.test(map.get(new Key(5)) == 10);
        Asserts.test(map.get(new Key(6)) == 11);
        Asserts.test(map.get(new Key(7)) == 12);
        Asserts.test(map.get(new Key(8)) == 8);
    }

    static void test3(HashMap<Object, Integer> map) {
        map.put(null, 1); // 1
        map.put(new Object(), 2); // 2
        map.put("jack", 3); // 3
        map.put(10, 4); // 4
        map.put(new Object(), 5); // 5
        map.put("jack", 6);
        map.put(10, 7);
        map.put(null, 8);
        map.put(10, null);
        Asserts.test(map.size() == 5);
        Asserts.test(map.get(null) == 8);
        Asserts.test(map.get("jack") == 6);
        Asserts.test(map.get(10) == null);
        Asserts.test(map.get(new Object()) == null);
        Asserts.test(map.containsKey(10));
        Asserts.test(map.containsKey(null));
        Asserts.test(map.containsValue(null));
        Asserts.test(map.containsValue(1) == false);
    }

    static void test4(HashMap<Object, Integer> map) {
        map.put("jack", 1);
        map.put("rose", 2);
        map.put("jim", 3);
        map.put("jake", 4);
        map.remove("jack");
        map.remove("jim");
        for (int i = 1; i <= 10; i++) {
            map.put("test" + i, i);
            map.put(new Key(i), i);
        }
        for (int i = 5; i <= 7; i++) {
            Asserts.test(map.remove(new Key(i)) == i);
        }
        for (int i = 1; i <= 3; i++) {
            map.put(new Key(i), i + 5);
        }
        map.traversal(new Map.Visitor<Object, Integer>() {
            @Override
            public boolean visit(Object key, Integer value) {
                System.out.println(key + "_" + value);
                return false;
            }
        });
    }

    static void test5(HashMap<Object, Integer> map) {
        for (int i = 1; i <= 20; i++) {
            map.put(new SubKey1(i), i);
        }
        map.put(new SubKey2(1), 5);
        Asserts.test(map.get(new SubKey1(1)) == 5);
        Asserts.test(map.get(new SubKey2(1)) == 5);
        Asserts.test(map.size() == 20);
    }

    static void keyEqualsTest(){
        HashMap<Object, Integer> map = new LinkedHashMap<>();
        SubKey1 subKey1 = new SubKey1(10);
        SubKey2 subKey2 = new SubKey2(10);
        map.put(subKey1,1);
        map.put(subKey2,2);
        Asserts.test(map.size() == 1);
        Asserts.test(map.containsKey(new SubKey1(10)));
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