package com.raven.multithreaded.processrequest.v1;

import com.raven.multithreaded.processrequest.IRequestProcessor;
import com.raven.multithreaded.processrequest.Request;

import java.util.Objects;

/**
 * @PackageName: com.raven.multithreaded.processrequest.v1
 * @ClassName: SaveProcessor
 * @Blame: raven
 * @Date: 2021-08-12 14:40
 * @Description: 存储请求信息
 */
public class SaveProcessor implements IRequestProcessor {
    /**
     * 下一请求处理过程
     */
    private IRequestProcessor nextProcessor;

    public SaveProcessor() {
    }

    /**
     * 通过构造参数传递其他请求过程 达到链式调用的目的
     *
     * @param requestProcessor
     */
    public SaveProcessor(IRequestProcessor requestProcessor) {
        this.nextProcessor = requestProcessor;
    }

    @Override
    public void process(Request request) {
        System.out.println("SaveProcessor do sth");

        if (Objects.nonNull(nextProcessor)){
            nextProcessor.process(request);
        }
    }
}
