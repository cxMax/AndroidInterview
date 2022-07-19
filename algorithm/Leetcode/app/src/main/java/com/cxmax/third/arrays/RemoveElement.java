package com.cxmax.third.arrays;

/**
 * https://leetcode-cn.com/problems/remove-element/
 *
 * 27.移除元素
 *
 * Created by caixi on 2022/7/19.
 */
public class RemoveElement {

    /**
     * 双指针做法
     *
     * 快、慢两个指针，快指针遇到了，慢指针就停止前进
     *
     *
     * @param nums
     * @param val
     * @return 返回新的数组长度
     */
    public int removeElement(int[] nums, int val) {
        int slow = 0;
        int fast = 0;
        for (fast = 0;  fast < nums.length - 1; fast++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }
}
