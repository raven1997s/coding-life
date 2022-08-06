package com.raven.data_structures_and_algorithms.structure.base_13_trie;

import java.util.HashMap;

/**
 * Description:
 * date: 2022/8/1 13:10
 * 字典树 前缀树
 *
 * @author raven
 */
public class Trie<V> {
    private int size;
    private Node<V> root;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        root = null;
    }

    public V get(String key) {
        Node<V> node = node(key);
        return (node != null && node.word) ? node.value : null;
    }

    public V add(String key, V value) {
        keyCheck(key);

        if (root == null) {
            root = new Node<>(null);
        }
        int length = key.length();
        // 从根节点开始寻找
        Node<V> node = root;
        // 遍历每一个字符
        for (int i = 0; i < length; i++) {
            char c = key.charAt(i);
            boolean childrenEmpty = node.children == null;
            // 获取节点对应的节点映射集
            Node<V> childrenNode = childrenEmpty ? null : node.children.get(c);
            // 如果节点没有子节点 或者节点映射集中不存在字符对应的节点
            // 1。创建新节点
            // 2。把新节点和字符添加到节点映射集中
            if (childrenNode == null) {
                childrenNode = new Node<>(node);
                childrenNode.character = c;
                node.children = childrenEmpty ? new HashMap<>() : node.children;
                node.children.put(c, childrenNode);
            }
            // 继续向下寻找
            node = childrenNode;
        }

        // 之前存在单词。覆盖value返回
        if (node.word) {
            V oldValue = node.value;
            node.value = value;
            return oldValue;
        }

        // 之前不存在单词新建节点
        node.word = true;
        node.value = value;
        size++;
        return null;
    }

    public V remove(String key) {
        // 获取最后一个节点
        Node<V> lastNode = node(key);
        // 如果最后节点不存在，或者不是单词结尾，则不需要做任何事
        if (lastNode == null || !lastNode.word) {
            return null;
        }
        size --;
        V oldValue = lastNode.value;
        // 最后一个节点存在并且key也存在
        // 如果最后一个节点有子节点，并且子节点不为空，则只需要修改子节点中的单词为不存在即可
        if (lastNode.children != null && !lastNode.children.isEmpty()) {
            lastNode.word = false;
            lastNode.value = null;
            return oldValue;
        }

        // 如果最后一个节点没有子节点，则需要逐个删除字符
        Node<V> parent = null;
        while ((parent = lastNode.parent) != null) {
            // 从父节点的map中删除元素
            parent.children.remove(lastNode.character);
            // 如果父节点的map有其他元素则不继续删除
            if (parent.word || !parent.children.isEmpty()) {
                break;
            }
            lastNode = parent;
        }

        return oldValue;
    }

    public boolean contains(String key) {
        Node<V> node = node(key);
        // 找到单词并且单词存在
        return node != null && node.word;
    }

    public boolean startsWith(String prefix) {
        //  如果能顺着字符找到每一个字符 则是以指定字符开头
        return node(prefix) != null;
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
            if (node == null || node.children == null || node.children.isEmpty()) {
                return null;
            }
            char c = key.charAt(i);
            // 获取节点对应的节点映射集
            node = node.children.get(c);
        }
        // 看字符串是否已经存在，还是只是其他字符串的一部分
        return node;
    }

    public void keyCheck(String key) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("key must not be empty");
        }
    }

    private static class Node<V> {
        // 父节点
        Node<V> parent;
        // 存储所有字符和相应的子节点路线
        HashMap<Character, Node<V>> children;
        // 字符key
        Character character;
        // 存储的数据
        V value;
        // 是否为一个完整的单词
        boolean word;

        public Node(Node<V> parent) {
            this.parent = parent;
        }
    }


}