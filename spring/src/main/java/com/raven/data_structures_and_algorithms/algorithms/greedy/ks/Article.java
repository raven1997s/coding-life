package com.raven.data_structures_and_algorithms.algorithms.greedy.ks;

/**
 * Description:
 * date: 2022/11/8 21:43
 * 背包
 * @author raven
 */
public class Article {

    public int weight;
    public int value;
    public double valueDensity;

    public Article(int weight, int value) {
        this.weight = weight;
        this.value = value;
        this.valueDensity = value * 1.0 / weight;
    }

    @Override
    public String toString() {
        return "Article{" +
                "weight=" + weight +
                ", value=" + value +
                ", valueDensity=" + valueDensity +
                '}';
    }
}