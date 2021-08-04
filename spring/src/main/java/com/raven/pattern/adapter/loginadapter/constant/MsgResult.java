package com.raven.pattern.adapter.loginadapter.constant;

import lombok.Data;

import java.io.Serializable;

/**
 * @PackageName: com.raven.pattern.strategy.pay
 * @ClassName: MsgResult
 * @Blame: raven
 * @Date: 2021-08-01 16:45
 * @Description: 结果封装
 */
@Data
public class MsgResult<T> implements Serializable {
    public static final String CODE_SUCCESS = "0";
    public static final String MESSAGE_SUCCESS = "success";

    /**
     * 状态码,0成功非0失败
     */
    private String code;

    /**
     * 状态码描述,0success非0失败原因
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    private MsgResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static MsgResult success() {
        return new MsgResult(CODE_SUCCESS, MESSAGE_SUCCESS, null);
    }

    public static <T> MsgResult success(T data) {
        return new MsgResult(CODE_SUCCESS, MESSAGE_SUCCESS, data);
    }

    public static <T> MsgResult success(T data, String message) {
        return new MsgResult(CODE_SUCCESS, message, data);
    }


    public static MsgResult error(ErrorCode errorCode, String appendMessage) {
        String message = String.format(errorCode.getMessage(), appendMessage == null ? "" : appendMessage);
        return new MsgResult(errorCode.getCode(), message, null);
    }

    public static MsgResult error(ErrorCode errorCode) {
        return new MsgResult(errorCode.getCode(), errorCode.getMessage(), null);
    }

    public static MsgResult error(String code, String message) {
        return new MsgResult(code, message, null);
    }

    public static MsgResult error(String message) {
        return new MsgResult(ErrorCode.OPER_FAIL.getCode(), message, null);
    }

    public boolean hasSuccess() {
        return CODE_SUCCESS.equals(this.code);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MsgResult{");
        sb.append("code='").append(this.code).append('\'');
        sb.append(", message='").append(this.message).append('\'');
        sb.append(", data=").append(this.data);
        sb.append('}');
        return sb.toString();
    }

    public String toSimpleString() {
        StringBuilder sb = new StringBuilder("MsgResult{");
        sb.append("code='").append(this.code).append('\'');
        sb.append(", message='").append(this.message).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }
}
