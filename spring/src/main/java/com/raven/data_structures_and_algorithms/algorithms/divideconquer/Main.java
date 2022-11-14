package com.raven.data_structures_and_algorithms.algorithms.divideconquer;

/**
 * Description:
 * date: 2022/11/10 21:20
 * 分治 - 求最大的连续子序列和
 *
 * @author raven
 */
public class Main {
    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubarray2(nums));
    }

    /**
     * 分治法
     *
     * @param nums
     * @return
     */
    public static int maxSubarray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return maxSubarray(nums, 0, nums.length);
    }

    /**
     * 求解[begin,end)中最大连续子序列的和
     *
     * @param nums
     * @param begin
     * @param end
     * @return
     */
    public static int maxSubarray(int[] nums, int begin, int end) {
        if (end - begin < 2) {
            return nums[begin];
        }

        int mid = (end - begin) >> 1;
        // 求从mid开始递减到begin的最大连续子序列
        int leftMax = Integer.MIN_VALUE;
        int leftSum = 0;
        for (int i = mid-1; i <= begin; i--) {
            leftSum += nums[i];
            leftMax = Math.max(leftMax,leftSum);
        }
        int rightMax = Integer.MIN_VALUE;
        int rightSum = 0;
        for (int i = mid; i < end; i++) {
            rightSum += nums[i];
            rightMax = Math.max(rightMax,rightSum);
        }

        return Math.max(
                // 最大连续子序列横跨begin - mid - end；
                leftMax + rightMax,
                Math.max(
                        // 最大连续子序列在[begin,mid)
                        maxSubarray(nums, begin, mid),
                        // 最大连续子序列在[mid,end)
                        maxSubarray(nums, mid, end)
                ));
    }


    /**
     * 暴力解法
     *
     * @param nums
     * @return
     */
    public static int maxSubarray1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        // begin 记录从哪里开始 end记录到哪里结束 然后计算begin到end直接的和
        for (int begin = 0; begin < nums.length; begin++) {
            for (int end = begin; end < nums.length; end++) {
                int sum = 0;
                for (int i = begin; i <= end; i++) {
                    sum += nums[i];
                }
                max = Math.max(max, sum);
            }
        }
        return max;
    }


    /**
     * 暴力解法2
     *
     * @param nums
     * @return
     */
    public static int maxSubarray2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        // begin 记录从哪里开始 end记录到哪里结束 然后计算begin到end直接的和
        for (int begin = 0; begin < nums.length; begin++) {
            int sum = 0;
            for (int end = begin; end < nums.length; end++) {
                sum += nums[end];
                max = Math.max(max, sum);
            }
        }
        return max;
    }
}