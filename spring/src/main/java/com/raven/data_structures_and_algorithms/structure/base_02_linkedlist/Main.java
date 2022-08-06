package com.raven.data_structures_and_algorithms.structure.base_02_linkedlist;

import com.raven.data_structures_and_algorithms.structure.base_02_linkedlist.circle.CircleLinkedList;

/**
 * Description:
 * date: 2022/6/9 21:26
 *
 * @author raven
 */
public class Main {
    public static void main(String[] args) {
        josephus(2);
    }


    /**
     * 约瑟夫问题：
     * 一个环形列表(从1到8)，从第一个人从1开始报数，报到3的人被杀掉，剩下的人再从1开始报数，报到3的人被杀掉，循环操作，直到杀死最后一个人
     * 求所有死亡人的顺序
     */
    static void josephus(int n) {
        CircleLinkedList<Integer> linkedList = new CircleLinkedList<>();

        for (int i = 1; i <= 8; i++) {
            linkedList.add(i);
        }

        linkedList.reset();
        while (!linkedList.isEmpty()){
            for (int i = 0; i < n; i++) {
                linkedList.next();
            }
            System.out.println(linkedList.remove());
        }

    }

    static void testCircleSingleLinkedList() {
        List<Integer> linkedList = new CircleLinkedList<>();
        linkedList.add(1);
        System.out.println(linkedList);
        linkedList.add(2, 1);
        System.out.println(linkedList);
        linkedList.add(3, 0);
        System.out.println(linkedList);
        System.out.println("contains 1 : " + linkedList.contains(1));
        System.out.println(" index 0 :" + linkedList.get(0));
        System.out.println("2 : index  :" + linkedList.indexOf(2));
        System.out.println(linkedList);
        System.out.println("remove  index 0 , element : " + linkedList.remove(0));
        System.out.println("isEmpty : " + linkedList.isEmpty());
        System.out.println("old 0 element : " + linkedList.set(0, 3));
        System.out.println(linkedList);
        System.out.println("size :" + linkedList.size());
        linkedList.clear();
        System.out.println(linkedList);

        //ArrayList2<Object> list = new ArrayList2<>();
        //for (int i = 0; i < 100; i++) {
        //    list.add(i);
        //}
        //
        //for (int i = 0; i < 100; i++) {
        //    list.remove(0);
        //}
        //System.out.println(list);
    }
}