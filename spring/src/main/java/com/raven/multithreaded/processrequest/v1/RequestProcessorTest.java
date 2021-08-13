package com.raven.multithreaded.processrequest.v1;

import com.raven.multithreaded.processrequest.IRequestProcessor;
import com.raven.multithreaded.processrequest.Request;

/**
 * @PackageName: com.raven.multithreaded.processrequest.v1
 * @ClassName: RequestProcessorTest
 * @Blame: raven
 * @Date: 2021-08-12 14:43
 * @Description: 模拟处理请求案例 阻塞式处理问题
 */
public class RequestProcessorTest {
    public static void main(String[] args) {
        Request request = new Request();
        request.setName("processTest");
        // 基于责任链模式 自由组合完成对请求的处理
        IRequestProcessor processor = new SaveProcessor();
        processor = new PrintProcessor(processor);
        processor = new PreProcessor(processor);
        processor.process(request);
    }
}
