package com.raven.pattern.strategy.promotion;

/**
 * @PackageName: com.raven.pattern.strategy.promotion
 * @ClassName: IPromotionStratrgy
 * @Blame: raven
 * @Date: 2021-08-01 15:50
 * @Description: 定义营销活动策略规范
 */
public interface IPromotionStrategy {
    /**
     * 执行促销活动
     */
    void doPromotion();
}
