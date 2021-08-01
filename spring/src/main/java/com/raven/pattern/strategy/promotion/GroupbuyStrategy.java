package com.raven.pattern.strategy.promotion;

/**
 * @PackageName: com.raven.pattern.strategy.promotion
 * @ClassName: GroupbuyStrategy
 * @Blame: raven
 * @Date: 2021-08-01 15:49
 * @Description:
 */
public class GroupbuyStrategy implements IPromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("促销活动为团购活动,五人一起团购，每人减400元！");
    }
}
