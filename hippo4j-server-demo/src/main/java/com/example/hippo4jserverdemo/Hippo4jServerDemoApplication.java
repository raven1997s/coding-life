package com.example.hippo4jserverdemo;

import cn.hippo4j.core.enable.EnableDynamicThreadPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDynamicThreadPool
public class Hippo4jServerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Hippo4jServerDemoApplication.class, args);
    }

}
