package com.cxmax.third.arrays;

/**
 * 三刷
 * <p>
 * https://leetcode.cn/problems/binary-search/
 * <p>
 * Created by caixi on 2022/7/19.
 */
public class BinarySearch {

    /**
     * 左闭右闭
     * 1.确定左右，位数
     * 2.遍历
     * 3.写条件找middle
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

}
