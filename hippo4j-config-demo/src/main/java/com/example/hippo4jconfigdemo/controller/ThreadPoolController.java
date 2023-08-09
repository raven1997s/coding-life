package com.example.hippo4jconfigdemo.controller;

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


    @RequestMapping("/consumer/message-produce")
    public String get() {

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            messageConsumeDynamicExecutor.execute(() -> {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        return messageConsumeDynamicExecutor.getCorePoolSize() + " " + messageConsumeDynamicExecutor.getActiveCount();
    }
}