package com.raven.data_structures_and_algorithms.algorithms.base_01_排序.entity;

/**
 * Description:
 * date: 2022/8/8 14:50
 *
 * @author raven
 */
public class Stable implements Comparable<Stable> {
    public int num;
    public int score;

    @Override
    public int compareTo(Stable o) {
        return score - o.score;
    }

    public Stable() {
    }

    public Stable(int num, int score) {
        this.num = num;
        this.score = score;
    }
}