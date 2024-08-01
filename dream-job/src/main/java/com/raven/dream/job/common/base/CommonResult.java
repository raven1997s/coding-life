package com.raven.dream.job.common.base;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.raven.dream.job.common.exception.CommonException;
import com.raven.dream.job.common.utils.ToStrUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Objects;

/**
 * 统一数据返回实体类
 */
@Data
public class CommonResult<T> implements Serializable {

    @ApiModelProperty(value = "状态码: 1成功，其他值为具体的错误码，见：StatusEnum")
    private int status;

    @ApiModelProperty(value = "错误信息")
    private String message;

    @ApiModelProperty(value = "结果数据")
    private T data;

    public CommonResult() {
        this.status = StatusEnum.ERROR.getStatus();
        this.message = "";
    }

    private CommonResult(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public CommonResult<T> setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommonResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public CommonResult<T> setStatusEnum(StatusEnum statusEnum) {
        this.status = statusEnum.getStatus();
        this.message = statusEnum.getDesc();
        return this;
    }

    /**
     * 前端json序列化展示使用
     *
     * @return 结果数据
     */
    @JsonGetter("data")
    public T showData() {
        return data;
    }

    /**
     * 后端使用,在feign服务出错时自动抛出异常
     *
     * @return 结果数据
     */
    @JsonIgnore
    public T getData() {
        if (isSuccess()) {
            return data;
        } else {
            throw new CommonException(status, message);
        }
    }

    @JsonSetter("data")
    public CommonResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    @JsonIgnore
    public Boolean isSuccess() {
        if (this.status == StatusEnum.SUCCESS.getStatus()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean isFail() {
        return !isSuccess();
    }

    public static <E> CommonResult<E> success(E data) {
        CommonResult<E> result = new CommonResult<>();
        result.setData(data);
        result.setStatus(StatusEnum.SUCCESS.getStatus());
        result.setMessage("操作成功");
        return result;
    }

    public static CommonResult<Void> error(StatusEnum statusEnum) {
        return new CommonResult<>(statusEnum.getStatus(), statusEnum.getDesc(), null);
    }

    public static CommonResult<Void> error(int code, String message) {
        return new CommonResult<>(code, message, null);
    }

    public static CommonResult<Void> error(String message) {
        return new CommonResult<>(StatusEnum.ERROR.getStatus(), message, null);
    }

    public static CommonResult<Void> success() {
        return success(null);
    }

    public CommonResult setSuccess() {
        return this.setStatus(StatusEnum.SUCCESS.getStatus());
    }

    public CommonResult setSuccess(String msg) {
        return this.setMessage(msg).setSuccess();
    }

    public boolean hasSuccess() {
        return Objects.equals(1, this.status);
    }

    public static CommonResult error(Throwable e) {
        if (e instanceof CommonException) {
            CommonException se = (CommonException) e;
            int code = se.getStatus();
            String msg = StringUtils.isBlank(se.getMessage()) ? StatusEnum.ERROR.getDesc() : se.getMessage();
            return new CommonResult(code, msg, null);
        } else {
            return new CommonResult(StatusEnum.SYS_ERROR.getStatus(), StatusEnum.SYS_ERROR.getDesc() + ":" + e.getMessage(), null);
        }
    }

    public String toSimpleString() {
        StringBuilder sb = new StringBuilder("CommonResult{");
        sb.append("status='").append(this.status).append('\'');
        sb.append(", message='").append(this.message).append('\'');
        sb.append('}');
        return sb.toString();
    }

    /**
     * 状态枚举
     */
    public enum StatusEnum {
        SUCCESS(1, "成功"),
        ERROR(-1, "失败"),

        REQ_PARAM_ERROR(-2, "请求参数异常"),
        TOKEN_ERROR(-10, "TOKEN异常"),
        EXPERIED_ERROR(-30, "授权到期"),
        AUTHORITY_ERROR(-40, "鉴权失败"),
        INVALID_INNER_TOKEN_ERROR(-50, "内部服务调用鉴权异常"),
        SYS_ERROR(-60, "系统错误"),
        USER_NOT_EXIST_ERROR(-70, "用户不存在"),
        SMS_ERROR(-90, "短信验证码异常"),
        VERIFY_SIGN_ERROR(-100, "验签失败"),


        // ERP通用异常 20000 - 20999
        FILE_EXPORT_ERROR(20000, "文件导出异常"),

        // 基础服务异常 21000 - 21999

        // 库存服务异常 22000 - 21999

        // 计费服务异常 23000 - 23999

        // 订单服务异常 24000 - 24999

        // 生产管理服务异常 25000 - 25999

        // 采购服务异常 26000 - 26999
        ;

        @Getter
        private final Integer status;
        @Getter
        private final String desc;

        StatusEnum(Integer status, String desc) {
            this.status = status;
            this.desc = desc;
        }
    }

    @Override
    public String toString() {
        return ToStrUtils.toStr(this);
    }

}
