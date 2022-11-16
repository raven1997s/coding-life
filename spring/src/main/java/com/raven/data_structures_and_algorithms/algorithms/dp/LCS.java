package com.raven.data_structures_and_algorithms.algorithms.dp;

/**
 * Description:
 * date: 2022/11/16 14:18
 * 动态规划 - 最长公共子序列
 *
 * @author raven
 */
public class LCS {
    public static void main(String[] args) {
        int lcs = lcs(new int[]{1, 5, 9, 10}, new int[]{1, 4, 9, 10});
        System.out.println(lcs);
    }


    /**
     * 动态规划
     *
     * @param nums1
     * @param nums2
     * @return
     */
    static int lcs(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) {
            return 0;
        }
        if (nums2 == null || nums2.length == 0) {
            return 0;
        }

        // 定义dp数组
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }


        return dp[nums1.length][nums2.length];
    }

    /**
     * 递归实现
     *
     * @param nums1
     * @param nums2
     * @return
     */
    static int lcs1(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) {
            return 0;
        }
        if (nums2 == null || nums2.length == 0) {
            return 0;
        }
        return lcs1(nums1, nums1.length, nums2, nums2.length);
    }

    /**
     * 求nums1前i个元素和nums2前j个元素的最长公共子序列长度
     *
     * @param nums1
     * @param i
     * @param nums2
     * @param j
     * @return
     */
    static int lcs1(int[] nums1, int i, int[] nums2, int j) {
        if (i == 0 || j == 0) {
            return 0;
        }

        // 如果nums1[i - 1] == nums2[j - 1] 则最长子序列长度为lcs（xxxx） + 1
        if (nums1[i - 1] == nums2[j - 1]) {
            return lcs1(nums1, i - 1, nums2, j - 1) + 1;
        }
        return Math.max(lcs1(nums1, i - 1, nums2, j), lcs1(nums1, i, nums2, j - 1));
    }
}