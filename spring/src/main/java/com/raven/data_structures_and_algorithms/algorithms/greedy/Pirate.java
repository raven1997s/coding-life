package com.raven.data_structures_and_algorithms.algorithms.greedy;

import java.util.Arrays;

/**
 * Description:
 * date: 2022/11/8 20:14
 * 贪心-- 最优装载问题(加勒比海盗)
 *
 * @author raven
 */
public class Pirate {
    public static void main(String[] args) {
        int[] weights = {3, 5, 4, 10, 7, 14, 2, 11};
        Arrays.sort(weights);

        int capacity = 30 ;
        int weight = 0;
        int count = 0;

        for (int w : weights) {
            int newWeight = w + weight;
            if (newWeight <= capacity){
                weight = newWeight;
                count++;
            }
        }

        System.out.println(count);
    }
}