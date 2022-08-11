package com.raven.data_structures_and_algorithms.exercise.排序;

import java.util.Arrays;

/**
 * Description:
 * date: 2022/8/11 09:40
 * https://leetcode.cn/problems/merge-sorted-array/
 *
 * @author raven
 */
public class _88_合并两个有序数组 {

    // 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
    //输出：[1,2,2,3,5,6]
    //解释：需要合并 [1,2,3] 和 [2,5,6] 。
    //合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i != n; i++) {
            nums1[m + i] = nums2[i];
        }
        Arrays.sort(nums1);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // 记录nums1开始比较元素到索引
        int nums1Index = 0;
        // 记录num1要比较元素结束到位置
        int nums1End = m;
        int nums2Index = 0;
        int nums2End = n;
        // 记录比较后要将结果放到数组中位置的索引。
        int currIndex = 0;
        // copy num1的元素到copyArray中
        int[] copyArray = new int[m];
        System.arraycopy(nums1, 0, copyArray, 0, m);

        // 比较copyArray 中元素和 nums2中元素，谁的大就将元素放到nums1中
        while (currIndex < nums1.length) {
            // 比较nums2的元素和copyArray的元素大小
            // 如果num2没有元素 则把copyArray中元素都放到num1中
            if (nums2Index >= nums2End) {
                nums1[currIndex++] = copyArray[nums1Index++];
            } else if (nums1Index >= nums1End || cmp(nums2[nums2Index], copyArray[nums1Index]) < 0) {
                // 如果nums2的元素小，则把nums2的元素放到nums1中
                nums1[currIndex++] = nums2[nums2Index++];
            } else {
                // 如果copyArray的元素小，则把copyArray的元素放到nums1中
                nums1[currIndex++] = copyArray[nums1Index++];
            }
        }
        Arrays.stream(nums1).forEach(System.out::println);
    }

    private static int cmp(int num1, int num2) {
        return num1 - num2;
    }

    public static void main(String[] args) {
        int[] nums1 = {0};
        int[] nums2 = {1};
        merge(nums1, 0, nums2, 1);
    }

}