package com.raven.data_structures_and_algorithms.algorithms.dp;

/**
 * Description:
 * date: 2022/11/14 22:40
 * 动态规划-求最少硬币数
 *
 * @author raven
 */
public class CoinChange {
    public static void main(String[] args) {
        System.out.println(coins(41));
    }

    /**
     * 计算换算面值为n的金额最少需要多少枚硬币
     *
     * @param n
     * @return
     */
    public static int coins(int n) {
        // 当剩余需要兑换的金额为负数时，一定不要这个结果
        if (n < 1) {
            return Integer.MAX_VALUE;
        }
        // 当硬币面值刚好等于额度时，返回需要一枚硬币
        if (n == 25 || n == 20 || n == 5 || n == 1) {
            return 1;
        }

        // 比较第一枚硬币取 25 20 5 1 时用硬币最少的就取他
        int min1 = Math.min(coins(n - 25), coins(n - 20));
        int min2 = Math.min(coins(n - 5), coins(n - 1));
        return Math.min(min1, min2) + 1;
    }
}