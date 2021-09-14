package com.raven.multithreaded.map.concurrenthashmap;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @PackageName: com.raven.multithreaded.map.concurrenthashmap
 * @ClassName: CHMTest
 * @Blame: raven
 * @Date: 2021-09-07 18:35
 * @Description:
 */
public class CHMTest {

    static ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            concurrentHashMap.put("key" + i,i);
        }
        HashMap hashMap = new HashMap();
        System.out.println("Hello World!");
    }
}
