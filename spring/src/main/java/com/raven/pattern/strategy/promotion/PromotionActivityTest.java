package com.raven.pattern.strategy.promotion;

/**
 * @PackageName: com.raven.pattern.strategy.promotion
 * @ClassName: PromotionActivityTest
 * @Blame: raven
 * @Date: 2021-08-01 16:14
 * @Description: 创建促销活动 指定活动名称 根据不同的活动名称返回不同的促销活动内容
 */
public class PromotionActivityTest {
    public static void main(String[] args) {
        /**
         *  避免了if else 判断不同活动使用不同策略 促销活动工厂内部决定返回促销策略
         */
        PromotionActivity promotionActivity618 = new PromotionActivity(PromotionStrategyFactory.getPromotionStrategyByPromotionKey(""));
        promotionActivity618.execute();

        PromotionActivity promotionActivity1111 = new PromotionActivity(PromotionStrategyFactory.getPromotionStrategyByPromotionKey("返现活动"));
        promotionActivity1111.execute();
    }
}
