package com.raven.pattern.strategy.promotion;

import lombok.Getter;

/**
 * @PackageName: com.raven.pattern.strategy.promotion
 * @ClassName: PromotionKey
 * @Blame: raven
 * @Date: 2021-08-01 16:01
 * @Description:
 */

public enum PromotionKeyEnum {
    EMPTY("无活动"),
    GROUP_BUY("团购活动"),
    CASH_BACK("返现活动")
    ;
    @Getter
    private String promotionName;

    PromotionKeyEnum(String promotionName) {
        this.promotionName = promotionName;
    }
}
