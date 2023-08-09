package com.example.hippo4jconfigdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * date: 2023/8/3 14:38
 *
 * @author longjiaocao
 */
@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Value(value = "${demo.data_id_name}")
    private String dataIdName;
    @Value(value = "${demo.group_name}")
    private String groupName;
    @Value(value = "${demo.env.name}")
    private String env;

//    @Value(value = "${spring.dynamic.thread-pool.executors}")
//    private String executors;

    @Value("${spring.dynamic.thread-pool.executors[0].core-pool-size}")
    private int corePoolSize;

    @Value("${spring.dynamic.thread-pool.executors[0].maximum-pool-size}")
    private int maximumPoolSize;

    @Value("${spring.dynamic.thread-pool.executors[0].thread-pool-id}")
    private String threadPoolId;

    @RequestMapping("/executors")
    public String getExecutors() {
        return " threadPoolId : " + threadPoolId + "\r\n" +
                " corePoolSize : " + corePoolSize + "\r\n" +
                " maximumPoolSize : " + maximumPoolSize;
    }

    @RequestMapping("/get")
    public String getDev() {
        return dataIdName + "-" + groupName + "-" + env;
    }
}
