package com.example.redissondemo.test;

import com.example.redissondemo.RedissonDemoApplication;
import com.example.redissondemo.test.domain.Order;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 测试学习redisson 对象 api
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedissonDemoApplication.class)
@Slf4j
public class RedissonObjectExampleTests {

    @Autowired
    private RedissonClient redissonClient;


    /**
     * 直接存储对象，无需强制类型转换 (支持异步操作)
     */
    @Test
    public void bucketTest() {
        RBucket<Order> orderBucket = redissonClient.getBucket("myOrder");
        System.out.println("init : " + orderBucket.get());

        // 设置
        orderBucket.set(new Order(10L, "OR001"), 1, TimeUnit.MINUTES);
        System.out.println("设置 : " + orderBucket.get());

        // 删除
        System.out.println("删除状态：" + orderBucket.delete() + "当前" + orderBucket.get());

        // 重新设置
        orderBucket.set(new Order(10L, "OR001"), 1, TimeUnit.MINUTES);
        System.out.println("重新设置 : " + orderBucket.get());

        // 如果是or1 改为or2
        orderBucket.compareAndSet(new Order(10L, "OR001"), new Order(10L, "OR002"));
        System.out.println("存在or1 改为or2 : " + orderBucket.get());

        // 如果是or1 改为or3
        orderBucket.compareAndSet(new Order(10L, "OR001"), new Order(10L, "OR003"));
        System.out.println("存在or1 改为or3 : " + orderBucket.get());

        // 存在bucket对象就修改为OR3
        orderBucket.setIfExists(new Order(10L, "OR003"), 1, TimeUnit.MINUTES);
        System.out.println("存在bucket对象就修改为OR3 : " + orderBucket.get());

        orderBucket.isExists();

        orderBucket.delete();

    }

    /**
     * 数字操作，计数器 原子类操作 等等 (支持异步操作)
     *
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws TimeoutException
     */
    @Test
    public void numberTest() throws ExecutionException, InterruptedException, TimeoutException {
        // 存在精度丢失
        RDoubleAdder doubleAdder = redissonClient.getDoubleAdder("doubleAdder");
        doubleAdder.add(1.9999);
        doubleAdder.add(2.0001);
        doubleAdder.add(3.0000000000000001);
        RFuture<Double> future = doubleAdder.sumAsync();
        Double sum = future.get(1000, TimeUnit.MILLISECONDS);
        System.out.println(sum);
        doubleAdder.delete();

        RLongAdder longAdder = redissonClient.getLongAdder("longAdder");
        longAdder.add(100);
        longAdder.increment();
        longAdder.increment();
        long longSum = longAdder.sum();
        System.out.println(longSum);
        longAdder.delete();
        longAdder.destroy();

        // 当不再使用整长型累加器对象的时候应该自行手动销毁，如果Redisson对象被关闭（shutdown）了，则不用手动销毁


        // 支持CAS设置
        RAtomicDouble atomicDouble = redissonClient.getAtomicDouble("atoDouble");
        double v = atomicDouble.incrementAndGet();
        System.out.println(v);

        atomicDouble.compareAndSet(v, 2.0);
        System.out.println(atomicDouble.get());
        atomicDouble.delete();

        RLongAdder atoLong = redissonClient.getLongAdder("atoLong");
        atoLong.increment();
        System.out.println(atoLong.sum());
        atoLong.delete();
    }

    /**
     * 限流*
     */
    @Test
    public void rateLimiterTest() {
        RRateLimiter limiter = redissonClient.getRateLimiter("orderImport");
        // 初始化
        // 最大流速 = 每1秒钟产生10个令牌
        // tryAcquire 尝试获取令牌
        // 如果令牌足够 则扣减令牌 返回true
        // 如果令牌不够 则不扣减  返回false

        // acquire 直接获取令牌 如果获取不到令牌 就阻塞等新的令牌产生
        // 如果令牌足够 则扣减对应的令牌
        // 如果令牌不够 则扣减令牌数量为0
        limiter.trySetRate(RateType.OVERALL, 10, 1, RateIntervalUnit.HOURS);

        boolean b = limiter.tryAcquire(1);
        System.out.println(" tryAcquire 1  availablePermits : " + limiter.availablePermits());
        b = limiter.tryAcquire(10);
        System.out.println(" tryAcquire 10 availablePermits : " + limiter.availablePermits());

        limiter.acquire(3);
        System.out.println("acquire 3 availablePermits : " + limiter.availablePermits());

        new Thread(() -> {
            limiter.acquire(2);
            System.out.println(" acquire 2 availablePermits : " + limiter.availablePermits());
        }).start();
        limiter.acquire(7);
        System.out.println(" acquire 7 availablePermits : " + limiter.availablePermits());

//        long start = System.currentTimeMillis();
        limiter.acquire(1);
//        System.out.println(System.currentTimeMillis() - start);
        System.out.println(" acquire 1 availablePermits : " + limiter.availablePermits());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        limiter.acquire(7);
        System.out.println(" acquire 7 availablePermits : " + limiter.availablePermits());

        limiter.delete();
    }


    @Test
    public void mapDemo() {
//        RMap<String, String> test = redissonClient.getMap("test", MapOptions.defaults());
//        test.put("testKey", "testValue");
//        test.fastPut("fastTestKey", "testValue");
//        String testKey = test.get("testKey");
//        test.remove("testKey");
//        test.get("testKey");

        RBucket<MyBucket> myBucket = redissonClient.getBucket("myBucket");
        myBucket.set(new MyBucket(), 1, TimeUnit.MINUTES);
        System.out.println(myBucket.get());

        myBucket.rename("myBucket1");
        RBucket<MyBucket> myBucket1 = redissonClient.getBucket("myBucket");
        System.out.println(myBucket1.get());
        RBucket<MyBucket> myBucket2 = redissonClient.getBucket("myBucket1");
        System.out.println(myBucket2.get());

    }

    @Data
    public static class MyBucket {
        private String name = "test";
        private int age = 10;
    }


}
