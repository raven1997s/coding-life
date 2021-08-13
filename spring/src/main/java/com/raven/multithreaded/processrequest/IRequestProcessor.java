package com.raven.multithreaded.processrequest;

/**
 * @PackageName: com.raven.multithreaded
 * @ClassName: IProcessor
 * @Blame: raven
 * @Date: 2021-08-12 14:29
 * @Description: 抽象处理器接口
 */
public interface IRequestProcessor {
    /**
     * 处理请求
     * @param request
     */
    void process(Request request);
}
