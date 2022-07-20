package com.raven.algorithm.structure.base_09_listset.set;

import com.raven.algorithm.structure.utils.Times;
import org.apache.commons.lang3.RandomUtils;

import java.util.Random;

/**
 * Description:
 * date: 2022/7/20 21:22
 *
 * @author raven
 */
public class Main {
    public static void main(String[] args) {
        //listSet();
    }

    static void treeSet(){
        // 从小到大有序大遍历
        TreeSet<Integer> listSet = new TreeSet<>();
        listSet.add(13);
        listSet.add(10);
        listSet.add(11);
        listSet.add(10);
        listSet.add(12);
        listSet.add(12);

        listSet.traversal(new Set.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });

    }
    static void listSet() {
        Set<Integer> listSet = new ListSet<>();
        listSet.add(10);
        listSet.add(10);
        listSet.add(11);
        listSet.add(10);
        listSet.add(12);
        listSet.add(12);

        listSet.traversal(new Set.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });

    }
}