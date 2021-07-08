package com.raven.order;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @PackageName: com.raven.order
 *  * @ClassName: OrderApplication
 * @Blame: raven
 * @Date: 2021-07-07 16:08
 * @Description: 模拟消费者服务
 * EnableDiscoveryClient注解: 允许服务注册发现到nacos(开启服务注册发现功能)
 * EnableFeignClients注解: 允许服务远程调用其他服务(开启服务远程调用功能)
 *  项目是多模块，包名不同，所以扫描不到，这样可以在注解中指定 basePackages
 */
@SpringBootApplication
//扫描Mapper
@MapperScan("com.raven.order.mapper")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.raven.driver.feign"})
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
