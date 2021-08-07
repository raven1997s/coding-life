package com.raven.pattern.decorator.battercake.v2;

/**
 * @PackageName: com.raven.pattern.decorator.battercake.v2
 * @ClassName: BattercakeTest
 * @Blame: raven
 * @Date: 2021-08-07 19:13
 * @Description: 通过装饰者模式封装后的卖煎饼案例
 */
public class BattercakeTest {

    public static void main(String[] args) {
        // 通过装饰者模式包装后，可以买+不同样式的煎饼，并且在需求变更后无需在创建更多的类
        Battercake battercake = new BaseBattercake();
        System.out.println("买" + battercake.getMsg() + "一共需要" + battercake.getPrice() + "元");

        battercake = new EggDecorator(battercake);
        System.out.println("买" + battercake.getMsg() + "一共需要" + battercake.getPrice() + "元");

        battercake = new EggDecorator(battercake);
        System.out.println("买" + battercake.getMsg() + "一共需要" + battercake.getPrice() + "元");

        battercake = new SausageDecorator(battercake);
        System.out.println("买" + battercake.getMsg() + "一共需要" + battercake.getPrice() + "元");
    }
}
