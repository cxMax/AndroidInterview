package com.cxmax.selftest.arrays;

/**
 *
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
 * Created by caixi on 2022/1/11.
 */
public class SortedSquares {

    /**
     * 使用双指针来做， 左、右进行比对。
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int[] ret = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            int slowRet = nums[left] * nums[left];
            int fastRet = nums[right] * nums[right];
            if (slowRet > fastRet) {
                ret[i] = slowRet;
                left++;
            } else {
                ret[i] = fastRet;
                right--;
            }
        }
        return ret;
    }
}
