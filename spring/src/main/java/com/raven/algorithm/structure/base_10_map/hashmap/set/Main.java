package com.raven.algorithm.structure.base_10_map.hashmap.set;

import com.raven.algorithm.structure.utils.FileInfo;
import com.raven.algorithm.structure.utils.Files;
import com.raven.algorithm.structure.utils.Times;
import com.raven.algorithm.structure.utils.executor.Executors;

/**
 * Description:
 * date: 2022/7/20 21:22
 *
 * @author raven
 */
public class Main {
    public static void main(String[] args) {
        //listSet();
        //testThread();
        readFile();
        //hashSet();
    }
    static void hashSet() {
        // 从小到大有序大遍历
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(13);
        hashSet.add(10);
        hashSet.add(11);
        hashSet.add(10);
        hashSet.add(12);
        hashSet.add(12);

        hashSet.traversal(new Set.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });

    }

    static void treeSet() {
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

    static void testThread(){
        Executors.execute( () -> {
            System.out.println("1111");
        });
    }
    static void readFile() {
        FileInfo fileInfo = Files.read("/Users/raven/Documents/devlop", new String[]{"java"});
        System.out.println("文件个数：" + fileInfo.getFiles());
        System.out.println("文件行数:" + fileInfo.getLines());
        String[] words = fileInfo.words();
        System.out.println("单词个数:" + words.length);

        //Times.test("listSet", () -> {
        //    ListSet<String> set = new ListSet<>();
        //    for (String word : words) {
        //        set.add(word);
        //    }
        //    for (String word : words) {
        //        set.contains(word);
        //    }
        //    for (String word : words) {
        //        set.remove(word);
        //    }
        //});
        //
        Times.test("treeSet", () -> {
            TreeSet<String> set = new TreeSet<>();
            for (String word : words) {
                set.add(word);
            }
            for (String word : words) {
                set.contains(word);
            }
            for (String word : words) {
                set.remove(word);
            }
        });
        Times.test("HashSet", () -> {
            HashSet<String> set = new HashSet<>();
            for (String word : words) {
                set.add(word);
            }
            for (String word : words) {
                set.contains(word);
            }
            for (String word : words) {
                set.remove(word);
            }
        });
        Times.test("LinkedHashSet", () -> {
            LinkedHashSet<String> set = new LinkedHashSet<>();
            for (String word : words) {
                set.add(word);
            }
            for (String word : words) {
                set.contains(word);
            }
            for (String word : words) {
                set.remove(word);
            }
        });
    }
}