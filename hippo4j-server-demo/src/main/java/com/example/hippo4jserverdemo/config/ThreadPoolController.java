package com.example.hippo4jserverdemo.config;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Description:
 * date: 2023/8/3 15:47
 *
 * @author longjiaocao
 */
@RestController
@RequestMapping("/config")
public class ThreadPoolController {


    @Resource
    private ThreadPoolExecutor messageConsumeDynamicExecutor;


    @Resource
    private ThreadPoolExecutor messageProduceDynamicExecutor;

    @RequestMapping("/consumer/info")
    public String getConsumerInfo() {
        return " corePoolSize : " + messageConsumeDynamicExecutor.getCorePoolSize() + "\r\n" +
                " maximumPoolSize : " + messageConsumeDynamicExecutor.getMaximumPoolSize();
    }


    @RequestMapping("/producer/info")
    public String getProducerInfo() {
        return " corePoolSize : " + messageProduceDynamicExecutor.getCorePoolSize() + "\r\n" +
                " maximumPoolSize : " + messageProduceDynamicExecutor.getMaximumPoolSize();
    }
    @RequestMapping("/consumer/message-produce")
    public String get() {

        for (int i = 0; i < 40; i++) {
            messageConsumeDynamicExecutor.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        return messageConsumeDynamicExecutor.getCorePoolSize() + " " + messageConsumeDynamicExecutor.getActiveCount();
    }
}