package com.cxmax.third.hash;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * https://leetcode-cn.com/problems/two-sum/
 *
 * 1. 两数之和
 *
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 *
 * Created by caixi on 2022/7/24.
 */
public class TwoSum {

    /**
     * 这道题是两数求和，返回下标
     *
     * 1. map存什么？ 元素和下标
     *
     *
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        Map<Integer, Integer> map = new HashMap<>();
        int[] ret = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                ret[0] = i;
                ret[1] = map.get(target - nums[i]);
            }
            map.put(nums[i], i);
        }
        return ret;

    }
}
