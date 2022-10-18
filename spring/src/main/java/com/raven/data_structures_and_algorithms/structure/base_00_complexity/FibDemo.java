package com.raven.data_structures_and_algorithms.structure.base_00_complexity;

import com.raven.utils.Times;

/**
 * Description:
 * date: 2022/4/28 11:20
 * 0 1 1 2 3 5 8 13 斐波那契数列
 *
 * @author raven
 */
public class FibDemo {

    /**
     * @param n
     * @return
     */
    // O(2^n)
    public static int fib1(int n) {
        return n < 0 ? 0 : n <= 1 ? n : fib1(n - 1) + fib1(n - 2);
    }

    // O(n)
    public static int fib2(int n) {
        if (n < 0) {
            return 0;
        }
        if (n <= 1) {
            return n;
        }
        // 0 1 2 3 4 5 6
        // 0 1 1 2 3 5 8 13 斐波那契数列
        int first = 0;
        int second = 1;
        int sum = 0;
        for (int i = 0; i < n - 1; i++) {
            sum = first + second;
            first = second;
            second = sum;
        }
        return sum;
    }

    public static int fib3(int n) {
        if (n < 0) {
            return 0;
        }
        if (n <= 1) {
            return n;
        }
        int first = 0;
        int second = 1;
        while (n-- > 1) {
            second += first;
            first = second - first;
        }
        return second;
    }

    public static int fib4(int n) {
        double c = Math.sqrt(5);
        return (int) ((Math.pow((1 + c) / 2, n) - Math.pow((1 - c) / 2, n)) / c);
    }

    public static int fib5(int n) {
        // 如果求前俩个数的值 直接返回
        if (n <= 2) {
            return 1;
        }
        // 构建数组 在数组每个位置记录对应的值
        int[] array = new int[n + 1];
        array[1] = array[2] = 1;
        return fib5(array, n);
    }

    private static int fib5(int[] array, int n) {
        // 如果当前位置有值 则之前计算过，直接返回即可
        if (array[n] == 0) {
            array[n] = fib5(array, n - 1) + fib5(array, n - 2);
        }
        return array[n];
    }

    public static int fib6(int n) {

        if (n <= 2) {
            return 1;
        }
        // 构建数组 在数组每个位置记录对应的值
        int[] array = new int[n + 1];
        array[1] = array[2] = 1;

        // 去掉递归
        for (int i = 3; i <= n; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array[n];
    }

    /**
     * 滚动数组
     *
     * @param n
     * @return
     */
    public static int fib7(int n) {
        if (n <= 2) {
            return 1;
        }
        // 构建滚动数组
        int[] array = new int[2];
        array[1] = array[0] = 1;
        for (int i = 3; i <= n; i++) {
            // array[i % 2] = array[(i - 1) % 2] + array[(i - 2) % 2]; % 2 等效与 & 1 通过二进制计算
            array[i & 1] = array[(i - 1) & 1] + array[(i - 2) & 1];
        }
        return array[n & 1];
    }


    public static int fib8(int n) {
        if (n <=  0) {
            return 0;
        }
        if (n <= 2) {
            return 1;
        }
        int first = 1;
        int second = 1;
        for (int i = 3; i <= n; i++) {
            second = second + first;
            first = second - first;
        }
        return second;
    }

    public static void main(String[] args) {
        Times.test("b", () -> System.out.println(fib4(100)));
        Times.test("a", () -> System.out.println(fib2(100)));
        Times.test("c", () -> System.out.println(fib8(100)));
        //Times.test("a", () -> System.out.println(fib3(2000000)));
        //Times.test("c", () -> System.out.println(fib2(40000)));
        //Times.test("D ", () -> System.out.println(fib4(4000)));
        //Times.test("E", () -> System.out.println(fib5(40000)));
    }
}