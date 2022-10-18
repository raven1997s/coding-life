package com.raven.data_structures_and_algorithms.exercise.数组;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.raven.utils.Asserts;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Description:
 * date: 2022/9/21 13:35
 * https://leetcode.cn/problems/minimum-number-of-days-to-make-m-bouquets/
 *
 * @author raven
 */
public class _1482_制作m束花所需的最少天数 {
    /**
     * 给你一个整数数组 bloomDay，以及两个整数 m 和 k 。
     * <p>
     * 现需要制作 m 束花。制作花束时，需要使用花园中 相邻的 k 朵花 。
     * <p>
     * 花园中有 n 朵花，第 i 朵花会在 bloomDay[i] 时盛开，恰好 可以用于 一束 花中。
     * <p>
     * 请你返回从花园中摘 m 束花需要等待的最少的天数。如果不能摘到 m 束花则返回 -1 。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：bloomDay = [1,10,3,10,2], m = 3, k = 1
     * 输出：3
     * 解释：让我们一起观察这三天的花开过程，x 表示花开，而 _ 表示花还未开。
     * 现在需要制作 3 束花，每束只需要 1 朵。
     * 1 天后：[x, _, _, _, _]   // 只能制作 1 束花
     * 2 天后：[x, _, _, _, x]   // 只能制作 2 束花
     * 3 天后：[x, _, x, _, x]   // 可以制作 3 束花，答案为 3
     * 示例 2：
     * <p>
     * 输入：bloomDay = [1,10,3,10,2], m = 3, k = 2
     * 输出：-1
     * 解释：要制作 3 束花，每束需要 2 朵花，也就是一共需要 6 朵花。而花园中只有 5 朵花，无法满足制作要求，返回 -1 。
     * 示例 3：
     * <p>
     * 输入：bloomDay = [7,7,7,7,12,7,7,7], m = 3, k = 3
     * 输出：12
     * 解释：要制作 2 束花，每束需要 3 朵。
     * 花园在 7 天后和 12 天后的情况如下：
     * 7 天后：[x, x, x, x, _, x, x]
     * 可以用前 3 朵盛开的花制作第一束花。但不能使用后 3 朵盛开的花，因为它们不相邻。
     * 12 天后：[x, x, x, x, x, x, x]
     * 显然，我们可以用不同的方式制作两束花。
     * 示例 4：
     * <p>
     * 输入：bloomDay = [1000000000,1000000000], m = 1, k = 1
     * 输出：1000000000
     * 解释：需要等 1000000000 天才能采到花来制作花束
     * 示例 5：
     * <p>
     * 输入：bloomDay = [1,10,2,9,3,8,4,7,5,6], m = 4, k = 2
     * 输出：9
     */


    public static void main(String[] args) {
        //https://leetcode.cn/problems/minimum-number-of-days-to-make-m-bouquets/solution/xiao-ming-chong-hua-by-xiaohu9527-5jf6/
        // bloomDay = [1,10,3,10,2], m = 3, k = 1
        // bloomDay = [1,10,3,10,2], m = 3, k = 2
        // bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
        // bloomDay = [1,10,2,9,3,8,4,7,5,6], m = 4, k = 2
        // bloomDay = [1000000000,1000000000], m = 1, k = 1

        //Asserts.test(minDays(new int[]{1, 10, 3, 10, 2}, 3, 1) == 3);
        //Asserts.test(minDays(new int[]{1, 10, 3, 10, 2}, 3, 2) == -1);
        //Asserts.test(minDays(new int[]{7, 7, 7, 7, 12, 7, 7}, 2, 3) == 12);
        //Asserts.test(minDays(new int[]{1, 10, 2, 9, 3, 8, 4, 7, 5, 6}, 4, 2) == 9);
        //Asserts.test(minDays(new int[]{1000000000, 1000000000}, 1, 1) == 1000000000);
    }

    /**
     * @param bloomDay
     * @param m        m束花
     * @param k        k朵连续的花
     * @return
     */
    public static int minDays(int[] bloomDay, int m, int k) {
        int count = bloomDay.length;
        if (count < m * k) {
            return -1;
        }
        // 记录所有的花的盛开情况，初始化时所有花都未盛开 （0代表鲜花未盛开 1代表鲜花盛开）
        int[] bloomOpenArray = new int[bloomDay.length];


        // 记录会有花开的日子
        Set<Integer> bloomOpenDaySet = Arrays.stream(bloomDay).boxed().collect(Collectors.toSet());

        // 最早开始开花的日子
        int min = Arrays.stream(bloomDay).min().getAsInt();

        int today = min - 1;
        // 默认不能打包
        boolean isPackaged = false;
        // 如果不能打包则时间继续流逝
        while (!isPackaged) {
            today++;
            // 今天没花开 跳过
            if (!bloomOpenDaySet.contains(today)) {
                continue;
            }
            //
            for (int i = 0; i < bloomDay.length; i++) {
                // 今天是哪一天 让花儿盛开
                if (today == bloomDay[i]) {
                    bloomOpenArray[i] = 1;
                }
            }

            //  如果当前盛开的花已经能够包装为花束 则返回天数
            if (isPackaged(bloomOpenArray, m, k)) {
                isPackaged = true;
            }
        }

        return today;
    }

    /**
     * 判断当前数组中是否包含m束连续k朵为1的花
     *
     * @param bloomOpenArray 鲜花的盛开情况
     * @param m              m束花
     * @param k              每束花需要k朵连续的花
     * @return
     */
    private static boolean isPackaged(int[] bloomOpenArray, int m, int k) {
        long count = Arrays.stream(bloomOpenArray).boxed().filter(i -> i == 1).count();
        // 如果盛开的花小于打包需要的最少花束 则一定不能打包成花束
        if (count < (long) m * k) {
            return false;
        }
        // 已经包装好的花束
        int packagedCount = 0;
        // 连续开花的次数
        int waitPackagedCount = 0;

        for (int isOpen : bloomOpenArray) {
            if (isOpen == 1) {
                waitPackagedCount++;
            } else {
                // 不是连续盛开的花，重置连续开花次数
                waitPackagedCount = 0;
                continue;
            }

            if (waitPackagedCount == k) {
                packagedCount++;
                waitPackagedCount = 0;
            }

            // 已打包好直接返回
            if (packagedCount == m) {
                return true;
            }
        }
        return false;
    }
}