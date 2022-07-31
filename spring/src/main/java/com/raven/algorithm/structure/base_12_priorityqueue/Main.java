package com.raven.algorithm.structure.base_12_priorityqueue;

import com.raven.algorithm.structure.base_12_priorityqueue.entity.Order;
import com.raven.algorithm.structure.base_12_priorityqueue.queue.PriorityQueue;

/**
 * Description:
 * date: 2022/7/31 10:47
 *
 * @author raven
 */
public class Main {

    public static void main(String[] args) {
        PriorityQueue<Order> queue = new PriorityQueue<>();
        queue.enQueue(new Order("OR00001",3));
        queue.enQueue(new Order("OR00002",7));
        queue.enQueue(new Order("OR00003",4));
        queue.enQueue(new Order("OR00004",10));

        // 订单按照orderNo 入队，出队根据订单数量优先级出队
        while (!queue.isEmpty()){
            System.out.println(queue.deQueue());
        }
    }
}