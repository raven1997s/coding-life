package com.raven.multithreaded.processrequest.v1;

import com.raven.multithreaded.processrequest.IRequestProcessor;
import com.raven.multithreaded.processrequest.Request;

import java.util.Objects;

/**
 * @PackageName: com.raven.multithreaded
 * @ClassName: PreProcessor
 * @Blame: raven
 * @Date: 2021-08-12 14:31
 * @Description: 预处理请求
 */
public class PreProcessor implements IRequestProcessor {

    /**
     * 下一请求处理过程
     */
    private IRequestProcessor nextProcessor;

    public PreProcessor() {
    }

    /**
     * 通过构造参数传递其他请求过程 达到链式调用的目的
     * @param requestProcessor
     */
    public PreProcessor(IRequestProcessor requestProcessor) {
        this.nextProcessor = requestProcessor;
    }

    @Override
    public void process(Request request) {
        System.out.println("PreProcessor do sth");
        if (Objects.nonNull(nextProcessor)){
            nextProcessor.process(request);
        }
    }
}
