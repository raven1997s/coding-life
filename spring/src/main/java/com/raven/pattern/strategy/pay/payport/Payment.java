package com.raven.pattern.strategy.pay.payport;

import com.raven.pattern.strategy.pay.MsgResult;

/**
 * @PackageName: com.raven.pattern.strategy.pay
 * @ClassName: PayMent
 * @Blame: raven
 * @Date: 2021-08-01 16:49
 * @Description: 支付方式
 */
public abstract class Payment {

    /**
     * 获取支付方式名称
     * @return
     */
    public abstract String getName();

    /**
     * 查询账户余额
     * @param uid
     * @return
     */
    public abstract double queryBalance(String uid);

    /**
     * 支付校验 当账号余额不足时，无法完成支付
     * @param uid
     * @param amount
     * @return
     */
    public MsgResult pay(String uid, double amount) {
        if (queryBalance(uid) < amount) {
            return new MsgResult(500, "支付失败", "余额不足！");
        }
        return new MsgResult(200, "支付成功", "支付金额:" + amount);
    }
}
