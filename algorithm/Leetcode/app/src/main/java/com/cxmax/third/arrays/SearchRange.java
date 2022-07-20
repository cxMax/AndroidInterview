package com.cxmax.third.arrays;

/**
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * 34.在排序数组中查找元素的第一个和最后一个位置
 *
 * 这道题，我觉得用双指针，更清晰一些。
 *
 * 1.先二分查找
 *
 * 2.再双指针，分别向左向右进行查找
 *
 * Created by caixi on 2022/7/20.
 */
public class SearchRange {

    /**
     * 这道题这么来解，
     *
     * 有序数组，有重复数组，要查找范围，
     *
     * 1. 先二分查找
     *
     * 2. 在分别双指针，左右移动，确定边界
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int index = binarySearch(nums, target);
        if (index == -1) {
            return new int[0];
        }
        // 确定左右边界
        int left = index;
        int right = index;
        // todo caixi 2022-7-20 这里要用移动后的位置去判断
        while(left - 1 >= 0 && nums[left - 1] == target) {
            left--;
        }
        // todo caixi 2022-7-20 这里要用移动后的位置去判断
        while(right + 1 < nums.length && nums[right + 1] == target) {
            right++;
        }
        return new int[]{left, right};
    }

    /**
     * 二分查找 左闭右闭写法
     *
     * @param nums
     * @param target
     * @return
     */
    public int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
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
