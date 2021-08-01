package com.raven.pattern.strategy.pay.payport;

/**
 * @PackageName: com.raven.pattern.strategy.pay
 * @ClassName: AliPay
 * @Blame: raven
 * @Date: 2021-08-01 16:54
 * @Description:
 */
public class AliPay extends Payment {
    @Override
    public String getName() {
        return "支付宝支付";
    }

    @Override
    public double queryBalance(String uid) {
        return 100;
    }
}
