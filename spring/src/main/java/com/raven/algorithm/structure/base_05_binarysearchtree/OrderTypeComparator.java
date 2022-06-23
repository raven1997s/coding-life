package com.raven.algorithm.structure.base_05_binarysearchtree;

import java.util.Comparator;

/**
 * Description:
 * date: 2022/6/23 21:46
 *
 * @author raven
 */
public class OrderTypeComparator implements Comparator<Order> {

    @Override
    public int compare(Order e1, Order e2) {
        return e1.getType() - e2.getType();
    }
}