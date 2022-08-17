package com.raven.data_structures_and_algorithms.structure.base_15_unionfind;

import com.raven.utils.Asserts;

/**
 * Description:
 * date: 2022/8/17 23:07
 *
 * @author raven
 */
public class Main {
    public static void main(String[] args) {
        UnionFind uf = new UnionFind(12);
        uf.union(0,4);
        uf.union(0,3);
        uf.union(0,1);
        uf.union(3,2);
        uf.union(3,5);
        Asserts.test(uf.isSame(2,5));

        uf.union(6,7);

        uf.union(9,8);
        uf.union(9,10);
        uf.union(9,11);
        Asserts.test(uf.isSame(10,11));
        Asserts.test(!uf.isSame(6,11));

        uf.union(0,6);
        Asserts.test(uf.isSame(4,7));

        uf.union(8,6);
        Asserts.test(uf.isSame(1,11));


    }
}