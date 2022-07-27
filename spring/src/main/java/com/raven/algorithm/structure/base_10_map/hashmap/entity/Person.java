package com.raven.algorithm.structure.base_10_map.hashmap.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * Description:
 * date: 2022/7/24 11:57
 *
 * @author raven
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {
    private int age;
    private float height;
    private String name;

    /**
     * 俩个对象比较：
     * 如果只重新equals方法，不重写hashCode方法
     * 因为索引位置的计算是hashCode & 数组长度 ，所以可能会出现hashCode不同，数据存储到同一位置，又因为重写了equals方法,所以可以替换对象，但结果较不稳定
     * 如果只重写hashCode方法，不重写equals方法
     * 因为索引位置的计算是hashCode & 数组长度 ，所以数据一定存储到数组同一位置，但因为没有重写equals方法，默认对象比较是比较地址值，所以一定不会替换对象
     */

    /**
     * 自定义对象作为 key，最好同时重写 hashcode 、 equals 方法
     * equals：用以判断2个key 是否为同一个 key
     * hashcode：必须保证 equals 为 true 的2 个key 的哈希值一样
     * 反过来 hashcode 相等的 key，不一定 equals 为 true
     *
     */
    /**
     * 哈希冲突时比较俩个key是否相等
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        // 内存地址相同 对象相同
        if (this == o) return true;
        // class类对象不同 对象一定不同
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Float.compare(person.height, height) == 0 && Objects.equals(name, person.name);
    }

    /**
     * map存储时通过hashCode方法找到指定的索引位置
     * @return
     */
    @Override
    public int hashCode() {
        int hashCode = Integer.hashCode(age);
        hashCode = hashCode * 31 + Float.hashCode(hashCode);
        hashCode = hashCode * 31 + (name != null ? name.hashCode() : 0);
        return hashCode;
    }
}