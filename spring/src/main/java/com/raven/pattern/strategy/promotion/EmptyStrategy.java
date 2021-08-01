package com.raven.pattern.strategy.promotion;

/**
 * @PackageName: com.raven.pattern.strategy.promotion
 * @ClassName: EmptyStrategy
 * @Blame: raven
 * @Date: 2021-08-01 15:47
 * @Description:
 */
public class EmptyStrategy implements IPromotionStrategy{
    @Override
    public void doPromotion() {
        System.out.println("现在没有活动！");
    }
}
