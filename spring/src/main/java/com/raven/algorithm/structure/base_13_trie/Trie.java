package com.raven.algorithm.structure.base_13_trie;

import com.raven.stream.function.VUtils;
import org.w3c.dom.Node;

import java.util.HashMap;

/**
 * Description:
 * date: 2022/8/1 13:10
 *
 * @author raven
 */
public class Trie<V> {
    private int size;
    private final Node<V> root = new Node<>();

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        root.children.clear();
    }

    public V get(String key) {
        Node<V> node = node(key);
        return node == null ? null : node.value;
    }

    public V add(String key, V value) {
        keyCheck(key);

        int length = key.length();
        // 从根节点开始寻找
        Node<V> node = root;
        // 遍历每一个字符
        for (int i = 0; i < length; i++) {
            char c = key.charAt(i);
            // 获取旧的节点下所有节点字符
            HashMap<Character, Node<V>> oldChildren = node.getChildren();
            node = oldChildren.get(c);
            if (node == null) {
                node = new Node<>();
                oldChildren.put(c, node);
            }
        }

        // 之前存在单词。覆盖value返回
        if (node.word) {
            V oldValue = node.value;
            node.value = value;
            return oldValue;
        } else {
            // 之前不存在单词新建节点
            node.word = true;
            node.value = value;
            size++;
        }
        return null;
    }

    public V remove(String key) {
        return null;
    }

    public boolean contains(String key) {
        return node(key) != null;
    }

    public boolean startsWith(String prefix) {
        return false;
    }

    /**
     * 根据字符串返回对应的节点
     *
     * @param key
     * @return
     */
    private Node<V> node(String key) {
        keyCheck(key);

        int length = key.length();
        // 从根节点开始寻找
        Node<V> node = root;
        // 遍历每一个字符
        for (int i = 0; i < length; i++) {
            char c = key.charAt(i);
            // 拿到字符看有没有对应的节点
            node = node.getChildren().get(c);
            if (node == null) {
                return null;
            }
        }
        // 看字符串是否已经存在，还是只是其他字符串的一部分
        return node.word ? node : null;
    }

    public void keyCheck(String key) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("key must not be empty");
        }
    }

    private static class Node<V> {
        // 存储所有字符和相应的子节点路线
        HashMap<Character, Node<V>> children;
        // 存储的数据
        V value;
        // 是否为一个完整的单词
        boolean word;

        public HashMap<Character, Node<V>> getChildren() {
            return children == null ? (children = new HashMap<>()) : children;
        }

    }


}