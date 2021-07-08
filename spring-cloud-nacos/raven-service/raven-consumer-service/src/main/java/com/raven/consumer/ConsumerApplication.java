package com.raven.consumer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @PackageName: com.raven.consumer
 * @ClassName: ConsumerApplication
 * @Blame: raven
 * @Date: 2021-07-08 18:05
 * @Description:
 */
@SpringBootApplication
//扫描Mapper
@MapperScan("com.raven.consumer.mapper")
/**
 * 项目是多模块，包名不同，所以扫描不到 需要加basePackages
 */
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.raven.driver.feign"})
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

}
