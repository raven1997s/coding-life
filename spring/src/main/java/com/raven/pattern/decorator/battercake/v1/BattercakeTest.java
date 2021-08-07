package com.raven.pattern.decorator.battercake.v1;

/**
 * @PackageName: com.raven.pattern.decorator.battercake.v1
 * @ClassName: BattercakeTest
 * @Blame: raven
 * @Date: 2021-08-07 18:55
 * @Description:测试类 传统方式对原有类"Battercake" 进行拓展，需要不断的创建新的类
 * 当需求发生轻微变更时就需要在创建新的类，来满足现有需求
 */
public class BattercakeTest {

    public static void main(String[] args) {
        Battercake battercake = new Battercake();
        System.out.println("买" + battercake.getMsg() + "一共需要" + battercake.getPrice() + "元");

        battercake = new BattercakeWithEgg();
        System.out.println("买" + battercake.getMsg() + "一共需要" + battercake.getPrice() + "元");

        battercake = new BattercakeWithSausage();
        System.out.println("买" + battercake.getMsg() + "一共需要" + battercake.getPrice() + "元");

        // 如果现在需要买加俩个鸡蛋，俩个火腿的煎饼，就需要创一个新的类，随着需要的变更，会有越来越多的类。

    }
}
