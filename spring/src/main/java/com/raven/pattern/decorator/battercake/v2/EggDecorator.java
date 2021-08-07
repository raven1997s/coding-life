package com.raven.pattern.decorator.battercake.v2;

/**
 * @PackageName: com.raven.pattern.decorator.battercake.v2
 * @ClassName: EggDecorator
 * @Blame: raven
 * @Date: 2021-08-07 19:10
 * @Description: 加1个鸡蛋的装饰器
 */
public class EggDecorator extends BattercakeDecorator {
    public EggDecorator(Battercake battercake) {
        super(battercake);
    }

    @Override
    public String getMsg() {
        return super.getMsg() + "+ 一个鸡蛋";
    }

    @Override
    public int getPrice() {
        return super.getPrice() + 1;
    }
}
