package com.raven.pattern.decorator.battercake.v1;

/**
 * @PackageName: com.raven.pattern.decorator.battercake.v1
 * @ClassName: BattercakeWithSausage
 * @Blame: raven
 * @Date: 2021-08-07 18:54
 * @Description: 一个煎饼+一个火腿
 */
public class BattercakeWithSausage extends Battercake {
    @Override
    public String getMsg() {
        return super.getMsg() + "+ 一个火腿";
    }

    @Override
    public int getPrice() {
        return super.getPrice() + 2;
    }
}
