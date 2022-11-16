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
        //System.out.println(coins4(41));
        System.out.println(coins(new int[]{1, 5, 20, 25}, 41));
    }

    public static int coins(int[] faces, int n) {
        if (n < 1 || faces == null || faces.length == 0) {
            return -1;
        }

        //  记录凑够n 需要的最少硬币数量
        int[] dp = new int[n + 1];
        // faces[i] 是凑过i分时最后选择的那枚硬币的面值
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int face : faces) {
                // 金额小于面值 无法兑换 尝试下一个面值
                if (i < face) {
                    continue;
                }
                //
                int value = i - face;
                if (dp[value] < 0 || dp[value] >= min) {
                    continue;
                }
                min = dp[value];
            }

            // 没有可用的面值 返回-1
            if (min == Integer.MAX_VALUE) {
                dp[i] = -1;
            } else {
                dp[i] = min + 1;
            }

        }
        return dp[n];

    }

    /**
     * 递推（自底向上） 动态规划
     * 描述钱都是通过哪些硬币凑成的
     *
     * @param n
     * @return
     */
    public static int coins4(int n) {
        if (n < 1) {
            return -1;
        }

        int[] dp = new int[n + 1];
        // faces[i] 是凑过i分时最后选择的那枚硬币的面值
        int[] faces = new int[dp.length];
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            if (i >= 1 && dp[i - 1] < min) {
                min = dp[i - 1];
                // 记录选取的是哪枚硬币
                faces[i] = 1;
            }

            if (i >= 5 && dp[i - 5] < min) {
                min = dp[i - 5];
                faces[i] = 5;
            }

            if (i >= 20 && dp[i - 20] < min) {
                min = dp[i - 20];
                faces[i] = 20;
            }

            if (i >= 25 && dp[i - 25] < min) {
                min = dp[i - 25];
                faces[i] = 25;
            }
            dp[i] = min + 1;
            print(faces, i);
        }
        //print(faces, n);
        return dp[n];
    }

    private static void print(int[] faces, int n) {
        System.out.print("[" + n + "] = ");
        while (n > 0) {
            System.out.print(faces[n] + " ");
            n -= faces[n];
        }
        System.out.println();
    }

    /**
     * 递推（自底向上） 动态规划
     *
     * @param n
     * @return
     */
    public static int coins3(int n) {
        if (n < 1) {
            return -1;
        }

        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            //int min = Integer.MAX_VALUE;
            //if (i >= 1) {
            //    min = Math.min(dp[i - 1], min);
            //}
            int min = dp[i - 1];

            if (i >= 5) {
                min = Math.min(dp[i - 5], min);
            }

            if (i >= 20) {
                min = Math.min(dp[i - 20], min);
            }

            if (i >= 25) {
                min = Math.min(dp[i - 25], min);
            }
            dp[i] = min + 1;
        }
        return dp[n];
    }


    /**
     * 记忆化搜索(自顶向下的调用)
     *
     * @param n 需要兑换的金额
     * @return
     */
    public static int coins2(int n) {
        // 当剩余需要兑换的金额为负数时
        if (n < 1) {
            return -1;
        }
        //初始化
        int[] dp = new int[n + 1];
        int[] faces = {1, 5, 20, 25};
        for (int face : faces) {
            if (n < face) {
                break;
            }
            dp[face] = 1;
        }
        return coins2(n, dp);
    }

    private static int coins2(int n, int[] dp) {

        // 当剩余需要兑换的金额为负数时，一定不要这个结果
        if (n < 1) {
            return Integer.MAX_VALUE;
        }

        if (dp[n] != 0) {
            return dp[n];
        }

        // 比较第一枚硬币取 25 20 5 1 时用硬币最少的就取他
        int min1 = Math.min(coins2(n - 25, dp), coins2(n - 20, dp));
        int min2 = Math.min(coins2(n - 5, dp), coins2(n - 1, dp));
        return Math.min(min1, min2) + 1;
    }

    /**
     * 计算换算面值为n的金额最少需要多少枚硬币
     * 暴力递归（自顶向下的调用，出现了重叠子问题）
     *
     * @param n
     * @return
     */
    public static int coins1(int n) {
        // 当剩余需要兑换的金额为负数时，一定不要这个结果
        if (n < 1) {
            return Integer.MAX_VALUE;
        }
        // 当硬币面值刚好等于额度时，返回需要一枚硬币
        if (n == 25 || n == 20 || n == 5 || n == 1) {
            return 1;
        }

        // 比较第一枚硬币取 25 20 5 1 时用硬币最少的就取他
        int min1 = Math.min(coins1(n - 25), coins1(n - 20));
        int min2 = Math.min(coins1(n - 5), coins1(n - 1));
        return Math.min(min1, min2) + 1;
    }
}