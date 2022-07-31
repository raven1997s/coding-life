package com.raven.algorithm.structure.base_12_priorityqueue.entity;

/**
 * Description:
 * date: 2022/7/31 10:47
 *
 * @author raven
 */
public class Order implements Comparable<Order>{

    private String orderNo;
    private int count;

    public Order(String orderNo, int count) {
        this.orderNo = orderNo;
        this.count = count;
    }

    @Override
    public int compareTo(Order o) {
        return this.count - o.count;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNo='" + orderNo + '\'' +
                ", count=" + count +
                '}';
    }
}