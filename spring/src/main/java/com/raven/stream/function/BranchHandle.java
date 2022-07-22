package com.raven.stream.function;

/**
 * Description:
 * date: 2022/7/22 14:13
 * 分支处理接口
 * @author raven
 */
@FunctionalInterface
public interface BranchHandle {

    /**
     * 分支操作
     * @param trueHandler 为true 要做的操作
     * @param falseHandler 为false 要做的操作
     */
    void trueOrFalseHandle(Runnable trueHandler, Runnable falseHandler);
}