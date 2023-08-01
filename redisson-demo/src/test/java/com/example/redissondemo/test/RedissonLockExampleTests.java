package com.example.redissondemo.test;

import com.example.redissondemo.RedissonDemoApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

/**
 * 测试学习redisson lock 集合 api
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedissonDemoApplication.class)
@Slf4j
public class RedissonLockExampleTests {

    @Autowired
    private RedissonClient redisson;

    public void reentrantTest() throws InterruptedException {

        RLock lock = redisson.getLock("anyLock");
        // 最常见的使用方法
        lock.lock();


        RLock fairLock = redisson.getFairLock("anyLock");
        // 最常见的使用方法
        fairLock.lock();
        // 10秒钟以后自动解锁
        // 无需调用unlock方法手动解锁
        fairLock.lock(10, TimeUnit.SECONDS);

        // 尝试加锁，最多等待100秒，上锁以后10秒自动解锁
        boolean res = fairLock.tryLock(100, 10, TimeUnit.SECONDS);

        fairLock.unlock();
    }
}
