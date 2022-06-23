package com.raven.algorithm.structure.base_05_binarysearchtree;

import org.w3c.dom.Element;

/**
 * Description:
 * date: 2022/6/23 21:23
 *
 * @author raven
 */
public class Order implements Comparable<Order> {
    private int id;
    private int type;

    public Order() {
    }

    public Order(int id) {
        this.id = id;
    }

    public Order(int id, int type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }

    ///**
    // * 当前订单和传入的订单id 比较 当前id > 传入的订单id  则排名考前
    // *
    // * @param order
    // * @return
    // */
    //@Override
    //public int compare(Order order) {
    //    return id - order.getId();
    //}

    @Override
    public int compareTo(Order order) {
        return id - order.getId();
    }
}