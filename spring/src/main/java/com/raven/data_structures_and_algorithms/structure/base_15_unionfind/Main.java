package com.raven.data_structures_and_algorithms.structure.base_15_unionfind;

import com.raven.data_structures_and_algorithms.structure.base_15_unionfind.entity.Person;
import com.raven.utils.Asserts;
import com.raven.utils.Times;
import org.apache.commons.lang3.StringUtils;

/**
 * Description:
 * date: 2022/8/17 23:07
 *
 * @author raven
 */
public class Main {
    public static final int count = 2000000;

    public static void main(String[] args) {
        //UnionFind uf = new QuickUnionUnionFind_Size(12);
        //UnionFind uf1 = new QuickUnionUnionFind(12);
        //UnionFind uf2 = new QuickFindUnionFind(12);
        //unionFindTest(uf);
        //unionFindTest(uf1);
        //unionFindTest(uf2);

        //unionFindTest(new QuickUnionUnionFind_Rank(12));
        //unionFindTest(new QuickUnionUnionFind_Size(12));
        //unionFindTest(new QuickUnionUnionFind_Rank_PathCompression(12));
        //unionFindTest(new QuickUnionUnionFind_Rank_PathSpilting(12));
        //unionFindTest(new QuickUnionUnionFind_Rank_PathHalving(12));
        //testTime(new QuickUnionUnionFind_Size(count));
        //testTime(new QuickUnionUnionFind_Rank(count));
        testTime(new QuickUnionUnionFind_Rank_PathCompression(count));
        testTime(new QuickUnionUnionFind_Rank_PathSpilting(count));
        testTime(new QuickUnionUnionFind_Rank_PathHalving(count));
        //testTime(new QuickUnionUnionFind(count));
        //testTime(new QuickFindUnionFind(count));
        testTime(new GenericQuickUnionFind<>());

        //testGenericQuickUnion();
        unionFindTest(new GenericQuickUnionFind<>());
    }

    public static void testGenericQuickUnion() {
        GenericQuickUnionFind<Person> uf = new GenericQuickUnionFind<>();
        Person p1 = new Person("zhangsan", 20);
        Person p2 = new Person("lisi", 80);
        Person p3 = new Person("wangwu", 30);
        Person p4 = new Person("zhaoliu", 40);
        uf.markSet(p1);
        uf.markSet(p2);
        uf.markSet(p3);
        uf.markSet(p4);
        uf.markSet(new Person("zhangsan", 20));

        Asserts.test(uf.isSame(null, null));

        uf.union(p1, p2);
        uf.union(p3, p4);

        Asserts.test(uf.isSame(p1, p2));
        Asserts.test(!uf.isSame(p1, p3));
        Asserts.test(uf.isSame(p3, p4));
        Asserts.test(StringUtils.equals(uf.find(p1).getName(), "zhangsan"));

        uf.union(p2,p4);
        Asserts.test(uf.isSame(p1, p3));
    }

    public static void testTime(UnionFind uf) {
        Times.test(uf.getClass().getName(), () -> {
            for (int i = 0; i < count; i++) {
                uf.union((int) (Math.random() * count), (int) (Math.random() * count));
            }

            for (int i = 0; i < count; i++) {
                uf.isSame((int) (Math.random() * count), (int) (Math.random() * count));
            }
        });
    }

    public static void testTime(GenericQuickUnionFind<Integer> uf) {
        Times.test(uf.getClass().getName(), () -> {
            for (int i = 0; i < count; i++) {
                uf.union((int) (Math.random() * count), (int) (Math.random() * count));
            }

            for (int i = 0; i < count; i++) {
                uf.isSame((int) (Math.random() * count), (int) (Math.random() * count));
            }
        });
    }
    public static void unionFindTest(GenericQuickUnionFind<Integer> uf) {
        for (int i = 0; i < count; i++) {
            uf.markSet(i);
        }
        uf.union(0, 4);
        uf.union(0, 3);
        uf.union(0, 1);
        uf.union(3, 2);
        uf.union(3, 5);
        Asserts.test(uf.isSame(2, 5));

        uf.union(6, 7);

        uf.union(9, 8);
        uf.union(9, 10);
        uf.union(9, 11);
        Asserts.test(uf.isSame(10, 11));
        Asserts.test(!uf.isSame(6, 11));

        uf.union(0, 6);
        Asserts.test(uf.isSame(4, 7));

        uf.union(8, 6);
        Asserts.test(uf.isSame(1, 11));
    }
}