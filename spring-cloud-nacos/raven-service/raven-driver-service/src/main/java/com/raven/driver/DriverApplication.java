package com.raven.driver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @PackageName: com.edaijia.edipao
 * @ClassName: DriverApplication
 * @Blame: raven
 * @Date: 2021-07-07 16:08
 * @Description: driver微服务
 * EnableDiscoveryClient注解: 允许服务注册发现到nacos
 */
@SpringBootApplication
//扫描Mapper
@MapperScan("com.raven.driver.mapper")
@EnableDiscoveryClient
//@EnableFeignClients
public class DriverApplication {

    public static void main(String[] args) {
        SpringApplication.run(DriverApplication.class, args);
    }
}
