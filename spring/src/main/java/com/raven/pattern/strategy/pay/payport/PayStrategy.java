package com.raven.pattern.strategy.pay.payport;

import java.util.HashMap;
import java.util.Map;

/**
 * @PackageName: com.raven.pattern.strategy.pay.payport
 * @ClassName: PayStrategy
 * @Blame: raven
 * @Date: 2021-08-01 16:57
 * @Description: 支付策略类 根据不同的支付方式封装返回不同的支付策略
 */
public class PayStrategy {
    private PayStrategy() {
    }

    public static final String ALI_PAY = "支付宝支付";
    public static final String WE_CHAT_PAY = "微信支付";

    private static Map<String, Payment> PAYMENT_MAP = new HashMap<>();

    static {
        PAYMENT_MAP.put(ALI_PAY, new AliPay());
        PAYMENT_MAP.put(WE_CHAT_PAY, new WechatPay());
    }

    public static Payment getPayment(String payType) {
        return PAYMENT_MAP.getOrDefault(payType, new AliPay());
    }
}
