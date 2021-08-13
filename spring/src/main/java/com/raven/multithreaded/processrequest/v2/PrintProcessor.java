package com.raven.multithreaded.processrequest.v2;

import com.raven.multithreaded.processrequest.IRequestProcessor;
import com.raven.multithreaded.processrequest.Request;

import java.util.Objects;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @PackageName: com.raven.multithreaded
 * @ClassName: PrintProcessor
 * @Blame: raven
 * @Date: 2021-08-12 14:31
 * @Description: 打印请求信息 并通过多线程的方式提高处理请求的效率
 */
public class PrintProcessor extends Thread implements IRequestProcessor {

    /**
     * 阻塞队列 承载请求列表
     */
    private LinkedBlockingDeque<Request> requests = new LinkedBlockingDeque<>();

    /**
     * 下一请求处理过程
     */
    private IRequestProcessor nextProcessor;

    public PrintProcessor() {
    }

    /**
     * 可通过有参构造进行责任链式处理请求
     *
     * @param nextProcessor
     */
    public PrintProcessor(IRequestProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }


    /**
     * 定义标识符，通过 @See shunDown()方法终止线程  通过volatile保证内存可见性
     */
    private volatile boolean isFinish = false;

    /**
     * 对外提供关闭的方法
     */
    public void shunDown() {
        isFinish = true;
    }

    @Override
    public void run() {
        while (!isFinish) {
            try {
                Request request = requests.take();
                System.out.println("PrintProcessor do sth " + request);
                if (Objects.nonNull(nextProcessor)) {
                    // 消费者 从阻塞队列中取请求进行消费
                    nextProcessor.process(request);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void process(Request request) {
        // 生产者，将请求加入到阻塞队列中
        requests.add(request);
    }
}
