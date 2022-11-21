package com.raven.data_structures_and_algorithms.structure.base_17_skiplist;

import java.util.Comparator;

/**
 * Description:
 * date: 2022/11/21 15:05
 *
 * @author raven
 */
public class SkipList<K, V> {
    private static final int MAX_LEVEL = 32;
    // 生成随机level的概率
    private static final double P = 0.25;
    private int size;
    /**
     * 有效层数
     */
    private int level;

    private Comparator<K> comparator;

    /**
     * 不存在任何K-V
     */
    private Node<K, V> first;

    public SkipList(Comparator<K> comparator) {
        this.comparator = comparator;
        first = new Node<>(null, null, MAX_LEVEL);
    }

    public SkipList() {
        this(null);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public V put(K key, V value) {
        keyCheck(key);
        Node<K, V> node = first;
        // 定义需要新增key的存储前驱结点
        Node<K, V>[] preNodes = new Node[level];
        // 从最高层开始逐层向下
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            // 如果当前值大于链表的值 继续向下传递
            while (node.nexts[i] != null && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            // 当前节点存在，将V重新赋值，返回旧的value
            if (cmp == 0) {
                V oldValue = node.nexts[i].value;
                node.nexts[i].value = value;
                return oldValue;
            }
            preNodes[i] = node;
        }

        // key对应的节点不存在，需要新增节点
        int newLevel = randomLevel();
        Node<K, V> newNode = new Node<>(key, value, newLevel);
        // 设置新节点的nexts ，以及前区节点的next
        for (int i = 0; i < newLevel; i++) {
            if (i >= level) {
                first.nexts[i] = newNode;
            } else {
                // 新节点的next
                newNode.nexts[i] = preNodes[i].nexts[i];
                // 前区节点的next
                preNodes[i].nexts[i] = newNode;
            }
        }
        // 节点数量增加
        size++;
        // 计算跳表的层数
        level = Math.max(level, newLevel);
        return null;
    }

    public V get(K key) {
        keyCheck(key);
        Node<K, V> node = first;
        // 从最高层开始逐层向下
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            // 如果当前值大于链表的值 继续向下传递
            while (node.nexts[i] != null && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            if (cmp == 0) {
                return node.nexts[i].value;
            }
        }
        return null;
    }

    public V remove(K key) {
        keyCheck(key);
        Node<K, V> node = first;
        // 定义需要删除key的存储前驱结点
        Node<K, V>[] preNodes = new Node[level];
        boolean exist = false;
        // 从最高层开始逐层向下
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            // 如果当前值大于链表的值 继续向下传递
            while (node.nexts[i] != null && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            // 当前节点存在，将V重新赋值，返回旧的value
            if (cmp == 0) {
                exist = true;
            }
            preNodes[i] = node;
        }

        // key对应的元素不存在，不需要进行删除操作
        if (!exist) {
            return null;
        }
        // 需要被删除的节点
        Node<K, V> removeNode = node.nexts[0];

        size--;

        // 设置后继
        for (int i = 0; i < removeNode.nexts.length; i++) {
            preNodes[i].nexts[i] = removeNode.nexts[i];
        }

        // 更新层数
        int newLevel = level;
        // first对应的nexts数组元素指向null  则这一层没有元素
        while (--newLevel > 0 && first.nexts[newLevel] == null) {
            level = newLevel;
        }

        return removeNode.value;
    }

    private int randomLevel() {
        int level = 1;
        while (Math.random() < P && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

    private int compare(K k1, K k2) {
        return comparator != null ? comparator.compare(k1, k2) : ((Comparable<K>) k1).compareTo(k2);
    }

    private void keyCheck(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
    }

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V>[] nexts;

        public Node(K key, V value, int level) {
            this.key = key;
            this.value = value;
            this.nexts = new Node[level];
        }
        @Override
        public String toString() {
            return key + ":" + value + "_" + nexts.length;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("一共" + level + "层").append("\n");
        for (int i = level - 1; i >= 0; i--) {
            Node<K, V> node = first;
            while (node.nexts[i] != null) {
                sb.append(node.nexts[i]);
                sb.append(" ");
                node = node.nexts[i];
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}