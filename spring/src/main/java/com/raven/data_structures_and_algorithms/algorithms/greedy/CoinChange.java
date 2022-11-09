package com.raven.data_structures_and_algorithms.algorithms.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Description:
 * date: 2022/11/8 20:31
 * 贪心--零钱兑换
 *
 * @author raven
 */
public class CoinChange {
    public static void main(String[] args) {
        // 可选的兑换方式
        Integer[] faces = {25, 10, 5, 1};
        Arrays.sort(faces, (o1, o2) -> o2 - o1);
        // 一共41分钱
        int money = 42;
        // 记录需要兑换次数
        int coins = 0;
        // 记录当前应该取哪个面值的硬币的索引
        int i = 0;
        while (i < faces.length) {
            // 剩余可兑换的钱中选取最大面值进行兑换
            // 可提取的钱小于faces[i]对应的面值，则取下一个面值的钱出来
            if (money < faces[i]) {
                i ++;
                continue;
            }

            money = money - faces[i];
            coins++;
            System.out.println("兑换 " + faces[i]);
        }
        System.out.println(coins);
    }

    public static void coinChange() {
        // 可选的兑换方式
        Integer[] faces = {25, 10, 5, 1};
        Arrays.sort(faces, (o1, o2) -> o2 - o1);
        // 一共41分钱
        int money = 42;
        // 记录需要兑换次数
        int coins = 0;
        while (money > 0) {
            for (Integer face : faces) {
                // 剩余可兑换的钱中选取最大面值进行兑换
                if (money < face) {
                    continue;
                }
                money = money - face;
                coins++;
                System.out.println("兑换 " + face);
                break;
            }
        }
        System.out.println(coins);
    }
}

