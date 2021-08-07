package com.raven.pattern.decorator.battercake.v2;

/**
 * @PackageName: com.raven.pattern.decorator.battercake.v2
 * @ClassName: BattercakeDecorator
 * @Blame: raven
 * @Date: 2021-08-07 19:05
 * @Description: 煎饼的装饰器父类，规范鸡蛋装饰器的行为，
 */
public class BattercakeDecorator extends Battercake {

    private Battercake battercake;

    public BattercakeDecorator(Battercake battercake) {
        this.battercake = battercake;
    }

    @Override
    public String getMsg() {
        return this.battercake.getMsg();
    }

    @Override
    public int getPrice() {
        return this.battercake.getPrice();
    }
}
