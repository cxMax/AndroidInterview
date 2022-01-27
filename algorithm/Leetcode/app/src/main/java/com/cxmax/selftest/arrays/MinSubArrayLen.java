package com.cxmax.selftest.arrays;

/**
 *
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
 * Created by caixi on 2022/1/13.
 */
public class MinSubArrayLen {

    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 慢指针
        int slow = 0;
        // 求和
        int sum = 0;
        // 结果的数组长度
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while(sum >= target) {
                ret = Math.min(ret, i + 1 - slow);
                sum -= nums[slow++];
            }
        }
        return ret == Integer.MAX_VALUE ? 0 : ret;


    }
}
