package com.raven.algorithm.dynamicarray;

/**
 * Description:
 * date: 2022/4/30 10:39
 *
 * @author raven
 */
public class Main {

    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add(99);
        list.add(88);
        list.add(77);
        list.add(66);
        System.out.println(list);
        list.remove(1);
        System.out.println(list);
        list.set(1,55);
        System.out.println(list);
        list.add(10,0);
        System.out.println(list);
        //Assert.test(list.contains(88));
        Assert.test(list.contains(99));
        Assert.test(list.size() == 4);
        //Assert.test(list.size() == 4);
        Assert.test(list.get(0) == 10);
        list.clear();
        //System.out.println(list.get(0));
    }
}