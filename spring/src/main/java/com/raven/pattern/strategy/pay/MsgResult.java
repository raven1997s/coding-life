package com.raven.pattern.strategy.pay;

/**
 * @PackageName: com.raven.pattern.strategy.pay
 * @ClassName: MsgResult
 * @Blame: raven
 * @Date: 2021-08-01 16:45
 * @Description: 支付结果封装
 */
public class MsgResult {
    private int code;
    private String msg;
    private Object data;

    public MsgResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "支付状态:" + "[" + code + "], " + msg + ", 交易详情：" + data;
    }
}
