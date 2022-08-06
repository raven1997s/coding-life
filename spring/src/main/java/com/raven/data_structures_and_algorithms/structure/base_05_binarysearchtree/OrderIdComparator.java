package com.raven.data_structures_and_algorithms.structure.base_05_binarysearchtree;

import java.util.Comparator;

/**
 * Description:
 * date: 2022/6/23 21:46
 *
 * @author raven
 */
public class OrderIdComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        return o1.getId() - o2.getId();
    }
}