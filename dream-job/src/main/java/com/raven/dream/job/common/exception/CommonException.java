package com.raven.dream.job.common.exception;


import com.raven.dream.job.common.base.CommonResult;

public class CommonException extends RuntimeException {

    private int status = CommonResult.StatusEnum.ERROR.getStatus();

    public CommonException() {
        super();
    }

    public CommonException(String message) {
        super(message);
    }

    public CommonException(String code, String message) {
        super(" [" + code + "] " + message);
    }

    public CommonException(int status, String message) {
        super(message);
        this.status = status;
    }

    public CommonException(CommonResult.StatusEnum statusEnum) {
        super(statusEnum.getDesc());
        this.status = statusEnum.getStatus();
    }

    public CommonException(int status, String code, String message) {
        super(" [" + code + "] " + message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
