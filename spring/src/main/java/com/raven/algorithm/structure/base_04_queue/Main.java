package com.raven.algorithm.structure.base_04_queue;

import com.raven.algorithm.structure.base_04_queue.circle.CircleDeque;
import com.raven.algorithm.structure.base_04_queue.circle.CircleQueue;

/**
 * Description:
 * date: 2022/6/18 21:22
 *
 * @author raven
 */
public class Main {

    public static void main(String[] args) {
        circleDeque();
    }

    private static void circleDeque() {
        CircleDeque<Integer> queue = new CircleDeque<>();
        for (int i = 0; i < 5; i++) {
            queue.enQueueRear(i);
        }
        // 0 1 2 3  4
        System.out.println(queue);
        for (int i = 5; i < 10; i++) {
            queue.enQueueFront(i);
        }
        //    8 7 6 5 0 1 2 3  4  ... 9
        System.out.println(queue);
        for (int i = 10; i < 13; i++) {
            queue.deQueueRear();
        }
        //    8 7 6 5 0 1   ... 9
        System.out.println(queue);

        //    .. 7 6 5 0 1   ...
        for (int i = 15; i < 17; i++) {
            queue.deQueueFront();
        }
        System.out.println(queue);
        while (!queue.isEmpty()) {
            System.out.println(queue.deQueueFront());
        }
    }

    private static void circleQueue() {
        CircleQueue<Integer> queue = new CircleQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enQueue(i);
        }
        System.out.println(queue);
        for (int i = 10; i < 15; i++) {
            queue.deQueue();
        }
        System.out.println(queue);

        for (int i = 15; i < 30; i++) {
            queue.enQueue(i);
        }
        System.out.println(queue);
        while (!queue.isEmpty()) {
            System.out.println(queue.deQueue());
        }
    }

    private static void deque() {
        Deque<Integer> numDeque = new Deque<>();
        numDeque.enQueueFront(11);
        numDeque.enQueueFront(12);
        numDeque.enQueueRear(13);
        numDeque.enQueueRear(14);

        while (!numDeque.isEmpty()) {
            System.out.println(numDeque.deQueueRear());
        }
    }

    private static void queue() {
        Queue<Integer> numQueue = new Queue<>();
        numQueue.enQueue(11);
        numQueue.enQueue(12);
        numQueue.enQueue(13);
        numQueue.enQueue(14);

        while (!numQueue.isEmpty()) {
            System.out.println(numQueue.deQueue());
        }
    }
}