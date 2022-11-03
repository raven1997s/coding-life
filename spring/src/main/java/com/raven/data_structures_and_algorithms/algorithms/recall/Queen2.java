package com.raven.data_structures_and_algorithms.algorithms.recall;

/**
 * Description:
 * date: 2022/11/2 21:20
 *
 * @author raven
 */
public class Queen2 {

    /**
     * 标记某一列是否有皇后
     */
    private boolean[] cols;

    /**
     * 标记某一斜线上是否有皇后(左上角 -> 右下角)
     */
    private boolean[] leftTop;

    /**
     * 标记某一斜线上是否有皇后(右上角 -> 左下角)
     */
    private boolean[] rightTop;

    /**
     * 一共有多少种摆法
     */
    private int ways;

    void placeQueens(int n) {
        if (n < 1) {
            return;
        }
        cols = new boolean[n];
        // 共 n * 2 -1 条斜线
        leftTop = new boolean[(n << 1) - 1];
        rightTop = new boolean[leftTop.length];
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
            // 数着的列如果有皇后 跳过
            if (cols[col]) {
                continue;
            }
            // 斜着的列如果有皇后 跳过
            int ltIndex = row - col + cols.length - 1;
            if (leftTop[ltIndex]) {
                continue;
            }
            int rtIndex = row + col;
            if (rightTop[rtIndex]) {
                continue;
            }

            // 记录皇后的摆法位置
            cols[row] = true;
            leftTop[ltIndex] = true;
            rightTop[rtIndex] = true;
            // 扫描下一列

            // 重置
            place(row + 1);
            cols[row] = false;
            leftTop[ltIndex] = false;
            rightTop[rtIndex] = false;
        }
    }


    private void show() {
        //for (int row = 0; row < cols.length; row++) {
        //    for (int col = 0; col < cols.length; col++) {
        //        if (cols[row] == col) {
        //            System.out.print("1 ");
        //        } else {
        //            System.out.print("0 ");
        //        }
        //    }
        //    System.out.println();
        //}
        System.out.println("==================");
    }

}