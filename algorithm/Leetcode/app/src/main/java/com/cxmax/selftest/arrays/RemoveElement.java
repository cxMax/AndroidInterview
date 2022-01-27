package com.cxmax.selftest.arrays;

/**
 *
 * https://leetcode-cn.com/problems/remove-element/
 *
 * 27.移除元素
 *
 * Created by caixi on 2022/1/5.
 */
public class RemoveElement {

    /**
     * 这道题，标准的双指针解法
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }

}
