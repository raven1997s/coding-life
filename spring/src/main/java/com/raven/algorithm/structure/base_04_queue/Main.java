package com.raven.algorithm.structure.base_04_queue;

/**
 * Description:
 * date: 2022/6/18 21:22
 *
 * @author raven
 */
public class Main {

    public static void main(String[] args) {
        deque();
    }

    private static void deque(){
        Deque<Integer> numDeque = new Deque<>();
        numDeque.enQueueFront(11);
        numDeque.enQueueFront(12);
        numDeque.enQueueRear(13);
        numDeque.enQueueRear(14);

        while (!numDeque.isEmpty()){
            System.out.println(numDeque.deQueueRear());
        }
    }
    private static void queue(){
        Queue<Integer> numQueue = new Queue<>();
        numQueue.enQueue(11);
        numQueue.enQueue(12);
        numQueue.enQueue(13);
        numQueue.enQueue(14);

        while (!numQueue.isEmpty()){
            System.out.println(numQueue.deQueue());
        }
    }
}