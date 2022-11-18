package com.raven.data_structures_and_algorithms.algorithms.dp;

/**
 * Description:
 * date: 2022/11/17 21:48
 * 动态规划-01背包问题
 * @author raven
 */
public class Knapsack {
    public static void main(String[] args) {
        System.out.println(maxValues(new int[]{6, 3, 5, 4, 6}, new int[]{2, 2, 6, 5, 4}, 10));
    }

    /**
     * 一维数组空间优化
     *
     * @param values
     * @param weights
     * @param capacity
     * @return
     */
    static int maxValues(int[] values, int[] weights, int capacity) {
        if (values == null || values.length == 0) {
            return 0;
        }
        if (weights == null || weights.length == 0) {
            return 0;
        }
        if (values.length != weights.length || capacity <= 0) {
            return 0;
        }

        // 定义dp数组
        // 假设dp(i,j) 是最大承重为j ,有钱i件物品可以选时的最大总价值
        int[] dp = new int[capacity + 1];
        for (int i = 1; i <= values.length; i++) {
            for (int j = capacity; j > 1; j--) {
                // 最后一件物品可以选，看选的价值搞还是不选的价值高
                if (j >= weights[i - 1]) {
                    dp[j] = Math.max(dp[j], dp[j - weights[i - 1]] + values[i - 1]);
                }
            }
        }
        return dp[capacity];
    }

    static int maxValues1(int[] values, int[] weights, int capacity) {
        if (values == null || values.length == 0) {
            return 0;
        }
        if (weights == null || weights.length == 0) {
            return 0;
        }
        if (values.length != weights.length || capacity <= 0) {
            return 0;
        }

        // 定义dp数组
        // 假设dp(i,j) 是最大承重为j ,有钱i件物品可以选时的最大总价值
        int[][] dp = new int[values.length + 1][capacity + 1];
        for (int i = 1; i <= values.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                // 如果容量j 小于i-1 的重量 ，则该物品无法选择
                if (j < weights[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 最后一件物品可以选，看选的价值搞还是不选的价值高
                    dp[i][j] = Math.max(dp[i - 1][j],
                            dp[i - 1][j - weights[i - 1]] + values[i - 1]);
                }
            }
        }
        return dp[values.length][capacity];
    }
}