package com.raven.data_structures_and_algorithms.algorithms.recursion;

/**
 * Description:
 * 爬楼梯
 * date: 2022/10/20 20:18
 * 楼梯有 n阶台阶，上楼可以一步上1阶，也可以一步上2阶，走完 n阶台阶共有多少种不同的走法？
 *
 * @author raven
 */
public class ClimbStairsDemo {

    /**
     * 假设 n个台阶有f（n）种走法，第一步有俩种走法，上一阶 or 上俩阶
     * 如果第一步上1个台阶，还剩下 n-1个台阶 剩下的台阶还有f（n-1）种走法
     * 如果第一步上2个台阶，还剩下 n-2个台阶 剩下的台阶还有f（n-2）种走法
     * 所以n个台阶 的走法应该为f（n-1） + f（n-2）种走法
     *
     * @param n 一共n个台阶
     * @return
     */
    private int climbStairs(int n) {
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    private int climbStairs2(int n) {
        if (n <= 2) {
            return n;
        }

        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            // 记录等于当前循环能走多少次
            second = first + second;
            // 初始化下次的起点，从second开始
            first = second - first;
        }
        return second;
    }
}