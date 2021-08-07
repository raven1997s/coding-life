package com.raven.pattern.decorator.battercake.v1;

/**
 * @PackageName: com.raven.pattern.decorator.battercake.v1
 * @ClassName: BattercakeWithEgg
 * @Blame: raven
 * @Date: 2021-08-07 18:52
 * @Description: 一个煎饼加一个鸡蛋
 */
public class BattercakeWithEgg extends Battercake{
    @Override
    public String getMsg() {
        return super.getMsg() + "+ 一个鸡蛋";
    }

    @Override
    public int getPrice() {
        return super.getPrice() + 1;
    }
}
