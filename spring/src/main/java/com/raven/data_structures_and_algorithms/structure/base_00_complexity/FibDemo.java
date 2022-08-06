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

    public static void main(String[] args) {
        //Times.test("a", () -> System.out.println(fib2(40)));
        //Times.test("b", () -> System.out.println(fib1(40)));
        Times.test("c", () -> System.out.println(fib3(400000)));
        Times.test("D ", () -> System.out.println(fib4(400000)));
    }
}