package com.raven.algorithm.structure.base_02_linkedlist;

/**
 * Description:
 * date: 2022/6/9 21:26
 *
 * @author raven
 */
public class Main {
    public static void main(String[] args) {
        List<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2, 1);
        linkedList.add(2, 0);
        System.out.println(linkedList);
        System.out.println("contains 1 : " + linkedList.contains(1));
        System.out.println(" index 0 :" + linkedList.get(0));
        System.out.println("2 : index  :" + linkedList.indexOf(2));
        System.out.println(linkedList);
        System.out.println("remove : " + linkedList.remove(0));
        System.out.println("isEmpty : " + linkedList.isEmpty());
        System.out.println( "old 0 element : " + linkedList.set(0, 3));
        System.out.println(linkedList);
        System.out.println("size :" +  linkedList.size());
        linkedList.clear();
        System.out.println(linkedList);
    }
}