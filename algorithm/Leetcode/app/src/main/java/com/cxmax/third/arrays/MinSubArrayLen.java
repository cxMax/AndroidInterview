package com.cxmax.third.arrays;

/**
 * 209. 长度最小的子数组
 *
 * https://leetcode-cn.com/problems/minimum-size-subarray-sum/
 *
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 *
 * 输出：2
 *
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 *
 * Created by caixi on 2022/7/19.
 */
public class MinSubArrayLen {

    /**
     * 滑动窗口来做，
     *
     * 什么是滑动窗口呢，还是双指针，
     *
     * 要根据题目定义三个参数，快慢指针，求和结果，
     *
     * @param target
     * @param nums
     * @return 他要返回满足条件，最小数组的长度
     */
    public int minSubArrayLen(int target, int[] nums) {
        // 异常判断
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 慢指针
        int slow = 0;
        // 求和
        int sum = 0;
        // 数组长度
        // todo caixi 2022-7-19 第一个错做了，就是这里要填最大值， 我觉得实际面试中，写0也应该没问题
        int ret = Integer.MAX_VALUE;
        // 快指针
        for (int fast = 0; fast < nums.length; fast++) {
            sum += nums[fast];
            // todo caixi 2022-7-19 第二个做错了的，是这里，当>=的时候，才进入条件
            // todo caixi 2022-7-19 第三个做错了的，是这里，这里要循环去滑动窗口，也就是慢指针的移动，有可能会移动几位，if只能移动一位，while要循环才得行
            while (sum >= target) {
                // 更新数组长度
                ret = Math.min(ret, fast - slow + 1);
                // 要把慢指针进1
                sum -= nums[slow];
                slow++;
            }
        }
        // todo caixi 2022-7-19 最后一个做错了，就是这里也要返回一个最大值，我觉得时机面试中，写0应该也不会有问题
        return ret == Integer.MAX_VALUE ? 0 : ret;

    }
}
