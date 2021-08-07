package com.raven.pattern.decorator.battercake.v2;

/**
 * @PackageName: com.raven.pattern.decorator.battercake.v2
 * @ClassName: SausageDecorator
 * @Blame: raven
 * @Date: 2021-08-07 19:13
 * @Description:
 */
public class SausageDecorator extends BattercakeDecorator {
    public SausageDecorator(Battercake battercake) {
        super(battercake);
    }

    @Override
    public String getMsg() {
        return super.getMsg() + "+ 一个火腿";
    }

    @Override
    public int getPrice() {
        return super.getPrice() + 2;
    }
}
