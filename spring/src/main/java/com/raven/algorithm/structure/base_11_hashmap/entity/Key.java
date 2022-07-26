package com.raven.algorithm.structure.base_11_hashmap.entity;

import java.util.Objects;

/**
 * Description:
 * date: 2022/7/25 23:04
 *
 * @author raven
 */
public class Key {
    int value;

    public Key() {
    }

    public Key(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        return value == key.value;
    }

    @Override
    public int hashCode() {
        return value / 20;
    }

    @Override
    public String toString() {
        return value + "";
    }
}