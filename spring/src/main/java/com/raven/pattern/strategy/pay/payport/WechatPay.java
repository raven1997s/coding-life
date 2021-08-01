package com.raven.pattern.strategy.pay.payport;

/**
 * @PackageName: com.raven.pattern.strategy.pay
 * @ClassName: WechatPay
 * @Blame: raven
 * @Date: 2021-08-01 16:54
 * @Description:
 */
public class WechatPay  extends Payment {
    @Override
    public String getName() {
        return "微信支付";
    }

    @Override
    public double queryBalance(String uid) {
        return 10;
    }
}
