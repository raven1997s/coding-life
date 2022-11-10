package com.raven.data_structures_and_algorithms.algorithms.greedy.ks;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Description:
 * date: 2022/11/8 21:42
 * 贪心-01背包问题
 *
 * @author raven
 */
public class Knapsack {
    public static void main(String[] args) {
        // 价值主导
        select("价值主导", getArticles(), (a, b) -> b.value - a.value);
        select("重量主导", getArticles(), Comparator.comparingInt(a -> a.weight));
        select("价值密度主导", getArticles(), (a, b) -> Double.compare(b.valueDensity, a.valueDensity));
    }


    public static void select(String title, Article[] articles, Comparator<Article> cmp) {
        System.out.println(title);
        Arrays.sort(articles, cmp);
        int capacity = 150;
        int weight = 0;
        int value = 0;
        LinkedList<Article> list = new LinkedList<>();
        for (int i = 0; i < articles.length && weight < capacity; i++) {
            int newWeight = weight + articles[i].weight;
            if (newWeight <= capacity) {
                weight = newWeight;
                value = value + articles[i].value;
                list.add(articles[i]);
            }
        }
        System.out.println("总价值：  " + value);
        list.forEach(System.out::println);
        System.out.println("-----------");
    }

    public static Article[] getArticles() {
        return new Article[]{
                new Article(35, 10),
                new Article(30, 40),
                new Article(60, 30),
                new Article(50, 50),
                new Article(40, 35),
                new Article(10, 40),
                new Article(25, 30),
        };
    }
}