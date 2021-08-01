package com.raven.pattern.strategy.promotion;

/**
 * @PackageName: com.raven.pattern.strategy.promotion
 * @ClassName: PromotionActivity
 * @Blame: raven
 * @Date: 2021-08-01 15:54
 * @Description: 定义促销活动，每一场促销活动都需要指定促销策略
 */
public class PromotionActivity {

    private IPromotionStrategy promotionStrategy;

    public PromotionActivity(IPromotionStrategy promotionStrategy) {
        this.promotionStrategy = promotionStrategy;
    }

    public void execute() {
        promotionStrategy.doPromotion();
    }
}
