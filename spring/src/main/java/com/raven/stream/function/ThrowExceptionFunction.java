package com.raven.stream.function;

/**
 * Description:
 * date: 2022/7/22 13:52
 * 抛异常接口
 * @author raven
 */
@FunctionalInterface
public interface ThrowExceptionFunction {
    /**
     * 抛出异常信息
     * @param message
     */
    void throwMessage(String message);
}