package com.cxmax.third.arrays;

/**
 * 977. 有序数组的平方
 *
 * https://leetcode-cn.com/problems/squares-of-a-sorted-array/
 *
 * 示例 2：
 *
 * 输入：nums = [-7,-3,2,3,11]
 *
 * 输出：[4,9,9,49,121]
 *
 * Created by caixi on 2022/7/19.
 */
public class SortedSquares {

    /**
     * 双指针来做
     * 有序数组，前后进行比较
     *
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int[] ret = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            int slow = nums[left] * nums[left];
            int fast = nums[right] * nums[right];
            if (slow > fast) {
                ret[i] = slow;
                left++;
            } else {
                ret[i] = fast;
                right--;
            }
        }
        return ret;
    }
}
