package com.example.redissondemo.test;

import com.example.redissondemo.RedissonDemoApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.*;
import org.redisson.config.Config;
import org.redisson.spring.starter.RedissonProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * 测试学习redisson 配置以及初始化过程
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedissonDemoApplication.class)
@Slf4j
public class RedissonConfigTests {


    @Autowired
    private RedisProperties redisProperties;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RedissonProperties redissonProperties;


    @Autowired
    private ApplicationContext ctx;

    @Test
    public void contextLoads() throws IOException {

        Config config = redissonClient.getConfig();
        System.out.println(config.toJSON());
    }


    @Test
    public void getSimpleYamlConfig() {
        Method clientNameMethod = ReflectionUtils.findMethod(RedisProperties.class, "getClientName");

        String clientName = null;
        if (clientNameMethod != null) {
            clientName = (String) ReflectionUtils.invokeMethod(clientNameMethod, redisProperties);
        }

        System.out.println(clientName);
    }

    @Test
    public void getYmlConfig() throws IOException {
        Config config = null;
        if (redissonProperties.getConfig() != null) {
            try {
                config = Config.fromYAML(redissonProperties.getConfig());
            } catch (IOException e) {
                try {
                    config = Config.fromJSON(redissonProperties.getConfig());
                } catch (IOException e1) {
                    e1.addSuppressed(e);
                    throw new IllegalArgumentException("Can't parse config", e1);
                }
            }
        } else {
            // TODO...
        }

        System.out.println(config.toJSON());
    }

    @Test
    public void getYmlJsonConfig() throws IOException {
        Config config = null;
        if (redissonProperties.getConfig() != null) {
            try {
                config = Config.fromYAML(redissonProperties.getConfig());
            } catch (IOException e) {
                try {
                    config = Config.fromJSON(redissonProperties.getConfig());
                } catch (IOException e1) {
                    e1.addSuppressed(e);
                    throw new IllegalArgumentException("Can't parse config", e1);
                }
            }
        } else if (redissonProperties.getFile() != null) {
            try {
                InputStream is = getConfigStream();
                config = Config.fromYAML(is);
            } catch (IOException e) {
                // trying next format
                try {
                    InputStream is = getConfigStream();
                    config = Config.fromJSON(is);
                } catch (IOException e1) {
                    e1.addSuppressed(e);
                    throw new IllegalArgumentException("Can't parse config", e1);
                }
            }
        }

        System.out.println(config.toJSON());
    }

    private InputStream getConfigStream() throws IOException {
        Resource resource = ctx.getResource(redissonProperties.getFile());
        return resource.getInputStream();
    }


}
