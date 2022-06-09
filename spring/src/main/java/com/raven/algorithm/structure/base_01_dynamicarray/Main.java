package com.raven.algorithm.structure.base_01_dynamicarray;

import com.raven.algorithm.structure.base_01_dynamicarray.entity.Car;

/**
 * Description:
 * date: 2022/4/30 10:39
 *
 * @author raven
 */
public class Main {

    public static void main(String[] args) {
        //ArrayList list = new ArrayList();
        //list.add(99);
        //list.add(88);
        //list.add(77);
        //list.add(66);
        //System.out.println(list);
        //list.remove(1);
        //System.out.println(list);
        //list.set(1,55);
        //System.out.println(list);
        //list.add(10,0);
        //System.out.println(list);
        ////Assert.test(list.contains(88));
        //Assert.test(list.contains(99));
        //Assert.test(list.size() == 4);
        ////Assert.test(list.size() == 4);
        //Assert.test(list.get(0) == 10);
        ////System.out.println(list.get(0));
        //for (int i = 0; i < 50; i++) {
        //    list.add(i);
        //}
        //System.out.println(list);
        //System.out.println(list.size());
        elementTest();
    }

    static void elementTest() {
        ArrayList<Car> carList = new ArrayList<>();
        carList.add(new Car("汽车", 12));
        carList.add(new Car("火车", 12));
        carList.add(null);
        carList.add(null);
        int i = carList.indexOf(null);
        System.out.println(i);
        System.out.println(carList);
        System.out.println(carList.remove(null));
        System.out.println(carList);
        carList.clear();
        // 提醒垃圾回收
        System.gc();
        //carList.remove(0);
        //carList.set(0, new Car("动车", 200));
        //ArrayList<Integer> ints = new ArrayList<>();
        //ints.add(1);
        //System.out.println(ints);
        //System.out.println(carList);
        //java.util.ArrayList<Object> objects = Lists.newArrayList();
    }
}