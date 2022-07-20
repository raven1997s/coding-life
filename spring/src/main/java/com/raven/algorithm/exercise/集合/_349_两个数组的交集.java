package com.raven.algorithm.exercise.集合;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * Description:
 * date: 2022/7/20 22:04
 * https://leetcode.cn/problems/intersection-of-two-arrays/
 *
 * @author raven
 */
public class _349_两个数组的交集 {
    public int[] intersection(int[] nums1, int[] nums2) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    set.add(nums1[i]);
                }
            }
        }

        return set.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] intersection2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return new int[0];
        }
        TreeSet<Integer> set = new TreeSet<>();
        TreeSet<Integer> resultSet = new TreeSet<>();
        for (int i : nums1) {
            set.add(i);
        }
        for (int i : nums2) {
            if (set.contains(i)) {
                resultSet.add(i);
            }
        }
        int index = 0;
        int[] result = new int[resultSet.size()];
        for (Integer i : resultSet) {
            result[index++] = i;
        }
        return result;
    }
}