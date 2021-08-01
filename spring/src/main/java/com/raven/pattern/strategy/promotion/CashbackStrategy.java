package com.raven.pattern.strategy.promotion;

/**
 * @PackageName: com.raven.pattern.strategy.promotion
 * @ClassName: CashbackStrategy
 * @Blame: raven
 * @Date: 2021-08-01 15:45
 * @Description:
 */
public class CashbackStrategy implements IPromotionStrategy{
    @Override
    public void doPromotion() {
        System.out.println("促销活动为返现活动，返现200元！");
    }
}
