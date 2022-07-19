package com.cxmax.selftest.hash;

import java.util.HashMap;

/**
 *
 * https://leetcode-cn.com/problems/two-sum/
 *
 * 1. 两数之和
 *
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 *
 * Created by caixi on 2022/1/28.
 */
public class TwoSum {

    /**
     * 使用hashmap来做， 但不能有重复元素
     *
     * hashmap , key - 元素, value - index 脚标
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int val = target - nums[i];
            if (map.containsKey(val)) {
                result[0] = i;
                result[1] = map.get(val);
            }
            map.put(nums[i], i);
        }
        return result;
    }

}
