package com.raven.data_structures_and_algorithms.algorithms.recursion;

/**
 * Description:
 * date: 2022/10/20 20:32
 * 3个柱子 将A的n个盘子移动到C（盘子到编号为[1,n]）
 *
 * @author raven
 */
public class HanoiDemo {

    public static void main(String[] args) {
        new HanoiDemo().hanoi(3, "A", "B", "C");
    }

    /**
     * 将n个盘子 从p1 移动到p3
     *
     * @param n  盘子数
     * @param p1 第一个柱子
     * @param p2 中间的柱子
     * @param p3 第二个柱子
     */
    private void hanoi(int n, String p1, String p2, String p3) {
        // 当n==1时，将盘子从
        if (n == 1) {
            move(1, p1, p3);
            return;
        }

        // n >1时
        // 将n-1个盘子 从 p1 移动到p2
        hanoi(n - 1, p1, p3, p2);
        // 将编号为 n到盘子 从p1 移动到 p3
        move(n, p1, p3);
        // 将n-1个盘子 从 p2 移动到 p3
        hanoi(n - 1, p2, p1, p3);
    }

    private void move(int n, String p1, String p3) {
        System.out.printf("把%d号盘子从%s移动到%s%n", n, p1, p3);
    }
}