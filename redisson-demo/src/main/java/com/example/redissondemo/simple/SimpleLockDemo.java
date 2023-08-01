package com.example.redissondemo.simple;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Description:
 * date: 2023/7/25 09:53
 *
 * @author longjiaocao
 */
public class SimpleLockDemo {

    private static int counter = 0;
    private static final int NUM_THREADS = 5;
    private static final int NUM_ITERATIONS = 100000;

    private static final RedissonClient REDISSON_CLIENT;
    static {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://124.70.31.82:6379")
                .setPassword("LiuNian0")
                .setDatabase(0);

        REDISSON_CLIENT =  Redisson.create(config);
    }

    public static void main(String[] args) {
        testRLock();
    }

    public  static void testRLock(){


        RLock lock = REDISSON_CLIENT.getLock("myLock");

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        for (int i = 0; i < NUM_THREADS; i++) {
            executor.execute(() -> {
                lock.lock();
                for (int j = 0; j < NUM_ITERATIONS; j++) {
                    // Increment the shared counter without synchronization
                    counter++;
                }
                lock.unlock();
            });
        }

        executor.shutdown();

        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final counter value: " + counter);
    }

}