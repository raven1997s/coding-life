package com.raven.data_structures_and_algorithms.algorithms.recall;

/**
 * Description:
 * date: 2022/11/2 21:20
 *
 * 回溯 n皇后问题
 * @author raven
 */
public class Queens {

    /**
     * 数组索引是行号,数组元素是列号
     */
    private int[] cols;

    /**
     * 一共有多少种摆法
     */
    private int ways;

    void placeQueens(int n) {
        if (n < 1) {
            return;
        }
        cols = new int[n];
        place(0);
        System.out.printf("皇后一共有%d种摆法%n", ways);
    }

    /**
     * 从第row行开始摆放皇后
     *
     * @param row
     */
    private void place(int row) {
        if (row == cols.length) {
            ways++;
            show();
            return;
        }

        for (int col = 0; col < cols.length; col++) {
            if (isValid(row, col)) {
                // 在第row行第col列摆放皇后
                cols[row] = col;
                // 扫描下一列
                place(row + 1);
            }
        }
    }

    private boolean isValid(int row, int col) {
        for (int i = 0; i < row; i++) {
            // 判断同一列是否存在皇后
            // 第col列有皇后
            if (cols[i] == col) {
                return false;
            }

            // 判断斜线上是否存在皇后
            // 第i行第皇后跟第row行第col列格子处于同一斜线上
            if (row - i == Math.abs(col - cols[i])) {
                return false;
            }
        }
        return true;
    }

    private void show() {
        for (int row = 0; row < cols.length; row++) {
            for (int col = 0; col < cols.length; col++) {
                if (cols[row] == col) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
        System.out.println("==================");
    }

}