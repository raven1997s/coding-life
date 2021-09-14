package com.raven.multithreaded.map.hashmap;

import com.google.common.collect.Maps;

import java.util.HashMap;

/**
 * @PackageName: com.raven.multithreaded.concurrentutil.map.hashmap
 * @ClassName: HashMapSourceAnalyse
 * @Blame: raven
 * @Date: 2021-09-05 20:44
 * @Description:
 */
public class HashMapSourceAnalyse {

    public static void main(String[] args) {
        HashMap<Object, Object> map = Maps.newHashMap();
        map.put("String","String");
        System.out.println(Integer.valueOf(100000).hashCode());
        System.out.println(Integer.valueOf(1000000000).hashCode());
        System.out.println(Integer.valueOf(100000).hashCode());

    }
}
