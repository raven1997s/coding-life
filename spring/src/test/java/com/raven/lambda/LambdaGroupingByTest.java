package com.raven.lambda;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.raven.entity.User;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @PackageName: com.raven.stream
 * @ClassName: LambdaGroupingByTest
 * @Blame: raven
 * @Date: 2021-06-02 15:55
 * @Description:
 */
public class LambdaGroupingByTest {
    private static List<User> userList = Lists.newArrayList();

    // https://www.cnblogs.com/kendoziyu/p/java8-stream.html
    static {
        userList.add(new User("张三",18,"西安"));
        userList.add(new User("张三",20,"北京"));
        userList.add(new User("李四",19,"北京"));
        userList.add(new User("李四",30,"延安"));
        userList.add(new User("李四",30,"上海"));
    }

    @Test
    public void Test1(){
        Map<String, List<User>> map = Maps.newHashMap();

//      API 1.   map.computeIfAbsent()
//            2.Collectors.groupingBy（）
//        3.Collectors.averagingInt

    }
    /**
     * groupingBy
     * https://blog.csdn.net/u014231523/article/details/102535902
     */
    /**
     * 求不同名字的平均年龄lambda表达式方式
     */
    @Test
    public void lambdaTest1(){
        // 按照不同名字分组，并且按子组收集数据
        Map<String, Double> avgAge = userList.stream().collect(Collectors.groupingBy(User::getName, Collectors.averagingInt(User::getAge)));
        System.out.println(avgAge);

        // 按照不同名字分组
        Map<String, List<User>> nameList = userList.stream().collect(Collectors.groupingBy(User::getName));
        System.out.println(nameList);

    }




}
