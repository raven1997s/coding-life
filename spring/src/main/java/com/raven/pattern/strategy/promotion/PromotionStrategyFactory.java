package com.raven.pattern.strategy.promotion;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @PackageName: com.raven.pattern.strategy.promotion
 * @ClassName: PromotionStrategyFactory
 * @Blame: raven
 * @Date: 2021-08-01 15:56
 * @Description: 促销活动工厂 懒汉式单例模式 + 策略模式
 */
public class PromotionStrategyFactory {
    private PromotionStrategyFactory() {
    }

    private static PromotionStrategyFactory factory;
    private static Map<String, IPromotionStrategy> PROMOTION_STRATEGY_MAP = new HashMap<>();

    static {
        factory = new PromotionStrategyFactory();
        PROMOTION_STRATEGY_MAP.put(PromotionKeyEnum.CASH_BACK.getPromotionName(), new CashbackStrategy());
        PROMOTION_STRATEGY_MAP.put(PromotionKeyEnum.GROUP_BUY.getPromotionName(), new GroupbuyStrategy());
    }

    public static PromotionStrategyFactory getFactory() {
        return factory;
    }

    /**
     * 根据促销活动名称返回促销活动内容
     * @param promotionName
     * @return
     */
    public static IPromotionStrategy getPromotionStrategyByPromotionKey(String promotionName) {
        IPromotionStrategy promotionStrategy = PROMOTION_STRATEGY_MAP.get(promotionName);
        return Objects.isNull(promotionStrategy) ? new EmptyStrategy() : promotionStrategy;
    }
}
