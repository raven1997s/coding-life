package com.example.redissondemo.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Description:
 * date: 2023/7/26 10:15
 *
 * @author longjiaocao
 */
//@Configuration
public class RedissonAutoConfiguration {


    @Value("${redisson.address}")
    private String addressUrl;
    @Value("${redisson.password}")
    private String password;

    @Bean
    public RedissonClient getRedisson() throws IOException {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(addressUrl)
                .setPassword(password)
                .setRetryInterval(5000)
                .setTimeout(10000)
                .setConnectTimeout(10000)
                .setConnectionMinimumIdleSize(22);
        System.out.println(config.toJSON());
        return Redisson.create(config);
    }

}