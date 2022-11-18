package com.raven.data_structures_and_algorithms.algorithms.dp;

import org.apache.commons.lang3.StringUtils;

/**
 * Description:
 * date: 2022/11/16 22:06
 *
 * @author raven
 */
public class LCSubstring {

    public static void main(String[] args) {
        System.out.println(lcs2("CABCD", "FEEGD"));
    }


    /**
     * 倒叙遍历 优化调用参数leftTop
     * @param str1
     * @param str2
     * @return
     */
    static int lcs2(String str1, String str2) {
        if (StringUtils.isEmpty(str1) || StringUtils.isEmpty(str2)) {
            return 0;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        // 假设 dp(i, j) 是以str1[i-1] str2[j-1]结尾的最长公共子串长度

        char[] rowChars = chars1;
        char[] colsChars = chars2;
        if (chars1.length < chars2.length) {
            colsChars = chars1;
            rowChars = chars2;
        }
        int[] dp = new int[colsChars.length + 1];
        int max = 0;
        for (int row = 1; row <= rowChars.length; row++) {
            for (int col = colsChars.length; col > 1; col--) {
                if (chars1[row - 1] != chars2[col - 1]) {
                    dp[col] = 0;
                } else {
                    dp[col] = dp[col - 1] + 1;
                    max = Math.max(max, dp[col]);
                }
            }
        }
        return max;
    }

    /**
     * 一维数组优化
     *
     * @param str1
     * @param str2
     * @return
     */
    static int lcs(String str1, String str2) {
        if (StringUtils.isEmpty(str1) || StringUtils.isEmpty(str2)) {
            return 0;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        // 假设 dp(i, j) 是以str1[i-1] str2[j-1]结尾的最长公共子串长度

        char[] rowChars = chars1;
        char[] colsChars = chars2;
        if (chars1.length < chars2.length) {
            colsChars = chars1;
            rowChars = chars2;
        }
        int[] dp = new int[colsChars.length + 1];
        int max = 0;
        for (int row = 1; row <= rowChars.length; row++) {
            int cur = 0;
            for (int col = 1; col <= colsChars.length; col++) {
                int leftTop = cur;
                cur = dp[col];
                if (chars1[row - 1] != chars2[col - 1]) {
                    dp[col] = 0;
                } else {
                    dp[col] = leftTop + 1;
                    max = Math.max(max, dp[col]);
                }
            }
        }
        return max;
    }

    static int lcs1(String str1, String str2) {
        if (StringUtils.isEmpty(str1) || StringUtils.isEmpty(str2)) {
            return 0;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        // 假设 dp(i, j) 是以str1[i-1] str2[j-1]结尾的最长公共子串长度
        int[][] dp = new int[chars1.length + 1][chars2.length + 1];
        int max = 0;
        for (int i = 1; i <= chars1.length; i++) {
            for (int j = 1; j <= chars2.length; j++) {
                if (chars1[i - 1] != chars2[j - 1]) {
                    continue;
                }
                dp[i][j] = dp[i - 1][j - 1] + 1;
                max = Math.max(max, dp[i][j]);
            }
        }

        return max;
    }
}