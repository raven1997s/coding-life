package com.raven.dream.job.common.exception;

import com.raven.dream.job.common.base.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResult<String> defaultExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        CommonResult<String> result = new CommonResult<>();

        if (e instanceof CommonException) {
            result.setStatus(((CommonException) e).getStatus());
        } else if (e instanceof MethodArgumentNotValidException) {
            FieldError fieldError = ((MethodArgumentNotValidException) e).getBindingResult().getFieldError();
            result.setStatus(CommonResult.StatusEnum.REQ_PARAM_ERROR.getStatus());
            String msg = "请求参数异常";
            if (Objects.nonNull(fieldError)) {
                msg = StringUtils.isBlank(fieldError.getDefaultMessage()) ? fieldError.getField() : fieldError.getDefaultMessage();
            }
            result.setMessage(msg);
            return result;
        } else {
            result.setStatus(CommonResult.StatusEnum.ERROR.getStatus());
        }

        return result.setMessage(e.getMessage());
    }
}