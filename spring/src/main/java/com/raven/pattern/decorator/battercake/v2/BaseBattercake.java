package com.raven.pattern.decorator.battercake.v2;

/**
 * @PackageName: com.raven.pattern.decorator.battercake.v2
 * @ClassName: BaseBattercake
 * @Blame: raven
 * @Date: 2021-08-07 19:03
 * @Description:描述一个基本的煎饼的信息
 */
public class BaseBattercake extends Battercake {
    @Override
    public String getMsg() {
        return "一个煎饼";
    }

    @Override
    public int getPrice() {
        return 5;
    }
}
