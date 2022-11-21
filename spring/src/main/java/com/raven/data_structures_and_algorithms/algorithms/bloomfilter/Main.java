package com.raven.data_structures_and_algorithms.algorithms.bloomfilter;

/**
 * Description:
 * date: 2022/11/18 15:45
 *
 * @author raven
 */
public class Main {
    public static void main(String[] args) {
        BloomFilter<Integer> bloomFilter = new BloomFilter<>(1_00_0000, 0.01);
        for (int i = 1; i <= 1_00_0000; i++) {
            bloomFilter.put(i);
        }

        int c = 0;
        for (int i = 1_00_0001; i <= 2_00_0000; i++) {
            if (bloomFilter.contains(i)) {
                c = c + 1;
            }
        }
        System.out.println(c);


    }
}