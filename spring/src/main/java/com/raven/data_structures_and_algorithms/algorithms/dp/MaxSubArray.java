package com.raven.data_structures_and_algorithms.algorithms.dp;

/**
 * Description:
 * date: 2022/11/16 10:12
 * 动态规划- 最大连续子序列和
 *
 * @author raven
 */
public class MaxSubArray {
    public static void main(String[] args) {
        System.out.println(maxSubArray2(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    /**
     * // 定义dp数组，dp[i] 以nums[i] 结尾的最大连续子序列和
     *
     * @param nums
     * @return
     */
    static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // 定义dp数组，dp[i] 以nums[i] 结尾的最大连续子序列和
        int[] dp = new int[nums.length];
        // 初始化
        dp[0] = nums[0];
        int max = dp[0];
        System.out.println("dp[0] = " + dp[0]);
        for (int i = 1; i < nums.length; i++) {
            // 如果dp[i - 1] 小于 0 那么dp[i] 的连续子序列和不需要加上dp[i - 1]
            if (dp[i - 1] < 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = dp[i - 1] + nums[i];
            }
            System.out.println("dp[" + i + "] = " + dp[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }


    /**
     * 优化空间
     *
     * @param nums
     * @return
     */
    static int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int dp = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 如果dp[i - 1] 小于 0 那么dp[i] 的连续子序列和不需要加上dp[i - 1]
            dp = dp < 0 ? nums[i] : dp + nums[i];
            max = Math.max(max, dp);
        }
        return max;
    }
}