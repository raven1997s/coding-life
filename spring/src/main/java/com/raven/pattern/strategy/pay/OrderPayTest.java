package com.raven.pattern.strategy.pay;

import com.raven.pattern.strategy.pay.payport.PayStrategy;

/**
 * @PackageName: com.raven.pattern.strategy.pay
 * @ClassName: OrderPayTest
 * @Blame: raven
 * @Date: 2021-08-01 17:05
 * @Description: 模拟用户使用不同支付方式支付订单
 * 根据用户选择的支付方式不同，调用不同的支付体系完成支付操作
 */
public class OrderPayTest {

    public static void main(String[] args) {
        Order order = new Order("0001", "OR00001", 90);
        MsgResult msgResult = order.pay(PayStrategy.WE_CHAT_PAY);
        System.out.println(msgResult);

        System.out.println("====================");
        Order order2 = new Order("0001", "OR00002", 90);
        MsgResult msgResult2 = order2.pay(PayStrategy.ALI_PAY);
        System.out.println(msgResult2);

    }
}
