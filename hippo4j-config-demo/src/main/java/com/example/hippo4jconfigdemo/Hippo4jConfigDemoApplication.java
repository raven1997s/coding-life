package com.example.hippo4jconfigdemo;

import cn.hippo4j.core.enable.EnableDynamicThreadPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDynamicThreadPool
public class Hippo4jConfigDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Hippo4jConfigDemoApplication.class, args);
    }

}
