package com.raven.data_structures_and_algorithms.structure.base_17_skiplist;

import com.raven.utils.Asserts;

/**
 * Description:
 * date: 2022/11/21 22:19
 *
 * @author raven
 */
public class Main {
    public static void main(String[] args) {
        SkipList<Integer, Integer> list = new SkipList<>();
        //test(list, 100, 10);
        test(list, 20, 5);
    }


    private static void test(SkipList<Integer, Integer> list, int count, int delta) {
        for (int i = 0; i < count; i++) {
            list.put(i, i + delta);
        }
		System.out.println(list);
        for (int i = 0; i < count; i++) {
            Asserts.test(list.get(i) == i + delta);
        }
        Asserts.test(list.size() == count);
        for (int i = 0; i < count; i++) {
            Asserts.test(list.remove(i) == i + delta);
        }
        Asserts.test(list.size() == 0);
    }
}