package com.raven.multithreaded.processrequest.v2;

import com.raven.multithreaded.processrequest.IRequestProcessor;
import com.raven.multithreaded.processrequest.Request;

/**
 * @PackageName: com.raven.multithreaded.processrequest.v2
 * @ClassName: MultiThreadRequestProcessorTest
 * @Blame: raven
 * @Date: 2021-08-12 20:57
 * @Description: 模拟处理请求场景并使用责任链模式 通过多线程的方式调高处理请求的效率
 */
public class MultiThreadRequestProcessorTest {

    private static IRequestProcessor requestProcessor;

    /**
     * 启动线程
     */
    public void setUp() {
        SaveProcessor saveProcessor = new SaveProcessor();
        saveProcessor.start();
        PrintProcessor printProcessor = new PrintProcessor(saveProcessor);
        printProcessor.start();
        requestProcessor = new PreProcessor(printProcessor);
        ((PreProcessor) requestProcessor).start();
    }

    public static void main(String[] args) {
        Request request = new Request();
        request.setName("MultiThreadRequestProcessorTest");
        new MultiThreadRequestProcessorTest().setUp();
        requestProcessor.process(request);
    }
}
