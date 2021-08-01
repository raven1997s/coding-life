package com.raven.pattern.strategy.pay;

import com.raven.pattern.strategy.pay.payport.PayStrategy;
import com.raven.pattern.strategy.pay.payport.Payment;

/**
 * @PackageName: com.raven.pattern.strategy.pay
 * @ClassName: Order
 * @Blame: raven
 * @Date: 2021-08-01 16:43
 * @Description: 订单对象 支付时调用支付接口
 */
public class Order {
    private String uid;
    private String orderId;
    private double amount;

    public Order(String uid, String orderId, double amount) {
        this.uid = uid;
        this.orderId = orderId;
        this.amount = amount;
    }

    /**
     * 订单支付 不同支付方式返回不同支付策略
     * @param payType
     * @return
     */
    public MsgResult pay(String payType) {
        Payment payment = PayStrategy.getPayment(payType);
        System.out.println("欢迎使用" + payment.getName() + "支付");
        System.out.println("支付订单号为：" + orderId);
        System.out.println("本次交易金额为" + amount + "元");
        return payment.pay(uid, amount);
    }
}
