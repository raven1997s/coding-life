package com.raven.algorithm.structure.base_10_map.map;

import com.raven.algorithm.structure.base_09_listset.set.TreeSet;
import com.raven.algorithm.structure.utils.FileInfo;
import com.raven.algorithm.structure.utils.Files;
import com.raven.algorithm.structure.utils.Times;
import com.raven.stream.function.VUtils;

/**
 * Description:
 * date: 2022/7/21 17:05
 *
 * @author raven
 */
public class Main {
    public static void main(String[] args) {
        fileCount();
    }

    static void base() {
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

    static void readFile() {
        FileInfo fileInfo = Files.read("/Users/raven/Documents/devlop", new String[]{"java"});
        System.out.println("文件个数：" + fileInfo.getFiles());
        System.out.println("文件行数:" + fileInfo.getLines());
        String[] words = fileInfo.words();
        System.out.println("单词个数:" + words.length);

        Times.test("TreeMap", () -> {
            TreeMap<String, Integer> map = new TreeMap<>();
            for (String word : words) {
                map.put(word, word.length());
            }
            for (String word : words) {
                map.containsKey(word);
            }
            for (String word : words) {
                map.remove(word);
            }
        });
    }

    static void fileCount() {
        FileInfo fileInfo = Files.read("/Users/raven/Documents/devlop/coding-life/spring/src/main/java/com/raven/algorithm/exercise", new String[]{"java"});
        System.out.println("文件个数：" + fileInfo.getFiles());
        System.out.println("文件行数:" + fileInfo.getLines());
        String[] words = fileInfo.words();
        System.out.println("单词个数:" + words.length);
        TreeMap<String, Integer> map = new TreeMap<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            Integer count = map.get(word);
            VUtils.isTrueOrFalse(count == null).trueOrFalseHandle(() -> {
                map.put(word, 1);
            }, () -> {
                map.put(word, count + 1);
            });
        }
        map.traversal(new Map.Visitor<String, Integer>() {
            @Override
            public boolean visit(String key, Integer value) {
                System.out.println(key + " : " + value);
                return false;
            }
        });

    }
}