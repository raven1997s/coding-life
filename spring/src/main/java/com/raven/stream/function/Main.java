package com.raven.stream.function;

import com.google.common.collect.Lists;
import com.raven.pattern.proxy.staticproxy.Person;

import java.util.List;

/**
 * Description:
 * date: 2022/7/22 13:59
 *
 * @author raven
 */
public class Main {
    public static void main(String[] args) {
        //list.add("aaa");
        //VUtils.isTrue(list.isEmpty()).throwMessage("list不能为空");
        //
        //VUtils.isTrueOrFalse(list.contains("张三")).trueOrFalseHandle(() -> System.out.println("张三存在。。。"), () -> System.out.println("张三不存在"));

        List<Person> list = Lists.newArrayList();
        list.add(new Person("张三",10));
        list.add(new Person("张三",10));
        list.add(new Person("张三",10));
        list.add(new Person("张三",10));
        list.add(new Person("张三",10));
        Integer integer = list.stream().map(Person::getAge).reduce((x1, x2) -> x1 * x2).get();
        System.out.println(integer);
    }
}