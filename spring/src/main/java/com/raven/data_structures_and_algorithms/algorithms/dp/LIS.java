package com.raven.data_structures_and_algorithms.algorithms.dp;

import java.util.Arrays;

/**
 * Description:
 * date: 2022/11/16 11:31
 * 动态规划-最长上升子序列个数
 * LIS:最长上升子序列(Longest Increasing Subsequence)
 *
 * @author raven
 */
public class LIS {
    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{10, 2, 2, 5, 1, 7, 101, 18}));
    }

    /**
     * 获取最长上升子序列个数
     *
     * @param nums
     * @return
     */
    static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // 最长上升序列的长度是所有dp[i] 中的最大值 max { dp(i) , i ∈ [0,dp.length}
        int[] dp = new int[nums.length];
        // 定义初始状态
        int max = dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            // 获取i前面的所有元素
            for (int j = 0; j < i; j++) {
                // 如果当前元素小于前面的元素则直接跳过
                if (nums[i] <= nums[j]) {
                    continue;
                }
                // 当前元素大于前面的元素时
                // dp[i] 和 dp[j] + 1 比较，将dp值大的设置为当前dp[i] 的值
                // dp[i] : dp[i] 之前的最大值
                // dp[j] + 1 : dp[i] 拼接上前面的最长序列
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}