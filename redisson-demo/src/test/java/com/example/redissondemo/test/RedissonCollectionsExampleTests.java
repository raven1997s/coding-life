package com.example.redissondemo.test;

import com.alibaba.fastjson.JSONObject;
import com.example.redissondemo.RedissonDemoApplication;
import com.example.redissondemo.test.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 测试学习redisson 集合 api
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedissonDemoApplication.class)
@Slf4j
public class RedissonCollectionsExampleTests {

    @Autowired
    private RedissonClient redissonClient;


    /**
     *  Java对象实现了java.util.concurrent.ConcurrentMap接口和java.util.Map接口。与HashMap不同的是，RMap保持了元素的插入顺序。
     *  还提供了异步（Async）、反射式（Reactive）和RxJava2标准的接口
     *
     */
    @Test
    public void mapTest() throws ExecutionException, InterruptedException {
        String key = "user";
        RMap<String, String> map = redissonClient.getMap(key);
        map.put("name", "zhangsan");
        map.put("age", "18");

        String name = map.get("name");
        System.out.println ("user name : " + name);

        boolean b = map.containsKey("age");
        System.out.println("has age ? : " + b);

        Set<Map.Entry<String, String>> entries = map.entrySet();
        System.out.println(Arrays.toString(entries.toArray()));
        map.remove("age");

        // fastPut 和 put 的区别就是 put会发回之前该索引位置的值（如果存在） 而fastPut 只会返回插入成功与否
        map.fastPut("like", "eat");
        System.out.println(JSONObject.toJSONString(map));
        // 同理
        map.fastRemove("like");

        // 异步操作 提高操作效率
        RFuture<String> putAsyncFuture = map.putAsync("321","");
        RFuture<Boolean> booleanRFuture = map.fastPutAsync("123", "");

        putAsyncFuture.get();
        booleanRFuture.get();
        System.out.println(JSONObject.toJSONString(map));
    }

    /**
     * 多值映射 一对多映射关系的存储
     * 基于list  set
     */
    @Test
    public void myMultimapTest(){
        // 基于set 基于Set的Multimap不允许一个字段值包含有重复的元素。
        RSetMultimap<String, String> map = redissonClient.getSetMultimap("RSetMultimap");
        map.put("age", "19");
        map.put("age", "20");
        map.put("name", "zhangsan");

        Set<String> allValues = map.get("age");
        System.out.println(allValues);
        List<String> newValues = Arrays.asList("17", "16", "15");
        Set<String> oldValues = map.replaceValues("age", newValues);
        System.out.println(oldValues);
        Set<String> removedValues = map.removeAll("age");
        System.out.println(removedValues);

        map.put("carCount", "2");
        Set<Map.Entry<String, String>> entries = map.entries();
        System.out.println(JSONObject.toJSONString(entries));
        map.delete();

        // 基于list 同理 基于List的Multimap在保持插入顺序的同时允许一个字段下包含重复的元素。
        redissonClient.getListMultimap("myListMultimap");


        //  Multimap对象的淘汰机制是通过不同的接口来实现的。它们是RSetMultimapCache接口和RListMultimapCache接口，分别对应的是Set和List的Multimaps。
        RListMultimapCache<Object, Object> myListMultimap2 = redissonClient.getListMultimapCache("myListMultimap2");
        myListMultimap2.expireKey("2", 10, TimeUnit.MINUTES);

        RSetMultimapCache<Object, Object> getSetMultimapCache = redissonClient.getSetMultimapCache("getSetMultimapCache");
        getSetMultimapCache.expireKey("2", 10, TimeUnit.MINUTES);

    }

    /**
     * 基于Redis的Redisson的分布式Set结构的RSet Java对象实现了java.util.Set接口。
     */
    @Test
    public void baseSet(){
        RSet<Order> set1 = redissonClient.getSet("anySet");
        set1.add(new Order());
        set1.remove(new Order());


        // 基于Redis的Redisson的分布式RSetCache Java对象在基于RSet的前提下实现了针对单个元素的淘汰机制
        /**
         * 目前的Redis自身并不支持Set当中的元素淘汰，因此所有过期元素都是通过org.redisson.EvictionScheduler实例来实现定期清理的。
         * 为了保证资源的有效利用，每次运行最多清理100个过期元素。
         * 任务的启动时间将根据上次实际清理数量自动调整，间隔时间趋于1秒到2小时之间。
         * 比如该次清理时删除了100条元素，那么下次执行清理的时间将在1秒以后（最小间隔时间）。
         * 一旦该次清理数量少于上次清理数量，时间间隔将增加1.5倍。
         */

        RSetCache<Object> set2 = redissonClient.getSetCache("anySet2");
        // ttl = 10 seconds
        set2.add(new Order(), 10, TimeUnit.SECONDS);


        // 基于Redis的Redisson的分布式RSortedSet Java对象实现了java.util.SortedSet接口。在保证元素唯一性的前提下，通过比较器（Comparator）接口实现了对元素的排序。
        RSortedSet<Integer> set = redissonClient.getSortedSet("anySet");
        set.trySetComparator(Comparator.comparingInt(a -> a)); // 配置元素比较器
        set.add(3);
        set.add(1);
        set.add(2);

        set.removeAsync(0);
        set.addAsync(5);
    }


    @Test
    public void baseListAndQueue(){
        // 基于Redis的Redisson分布式列表（List）结构的RList Java对象在实现了java.util.List接口的同时，确保了元素插入时的顺序。
        RList<Order> list = redissonClient.getList("anyList");
        list.add(new Order());
        Order order = list.get(0);
        list.remove(new Order());


        // 队列
        RQueue<Order> queue1 = redissonClient.getQueue("anyQueue");
        // 双端队列（Deque
        RDeque<Order> queue2 = redissonClient.getDeque("anyDeque");
        // 阻塞队列（Blocking Queue）
        RBlockingQueue<Order> queue3= redissonClient.getBlockingQueue("anyQueue");
        // 有界阻塞队列（Bounded Blocking Queue）
        RBoundedBlockingQueue<Order> queue4 = redissonClient.getBoundedBlockingQueue("anyQueue");

        // 延迟队列（Delayed Queue）
        // 基于Redis的Redisson分布式延迟队列（Delayed Queue）结构的RDelayedQueue Java对象在实现了RQueue接口的基础上提供了向队列按要求延迟添加项目的功能。
        // 该功能可以用来实现消息传送延迟按几何增长或几何衰减的发送策略。
        RDelayedQueue<Order> delayedQueue = redissonClient.getDelayedQueue(queue1);

        // 优先队列（Priority Queue）
        RPriorityQueue<Integer> queue = redissonClient.getPriorityQueue("anyQueue");

    }

}
