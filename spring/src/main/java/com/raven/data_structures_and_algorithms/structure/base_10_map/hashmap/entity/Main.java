package com.raven.data_structures_and_algorithms.structure.base_10_map.hashmap.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * date: 2022/7/24 12:06
 *
 * @author raven
 */
public class Main {
    public static void main(String[] args) {
        Person p1 = new Person(18,175.2f,"小熊");
        Person p2 = new Person(18,175.2f,"小熊");
        Map<Object, Object> map = new HashMap<>();
        map.put(p1,"aaa");
        map.put(p2,"aaa");
        System.out.println(map.size());
    }
}