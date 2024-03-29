package com.cxmax.leetcode.arrays;

import android.util.Log;

/**
 * @describe :
 * @usage :
 * <p>
 *     二分查找的两种写法 : （根据开闭区间来写判断条件）
 *     1. 【】左闭右闭
 *     2. 【）左闭右开
 *     时间复杂度 ：二分查找所需的时间复杂度为 O(logn)。
 * </p>
 * Created by caixi on 6/23/21.
 */
public class BinarySearch {

    public static void main(String[] args) {
        int target1 = BinarySearch.binarySearch1(new int[]{1,2,3,4}, 2);
        Log.e("info", "onCreate: 1" + target1);
        int target2 = BinarySearch.binarySearch2(new int[]{1,2,3,4}, 2);
        Log.e("info", "onCreate: 2" + target2);
    }


    /**
     * 第一种写法， 注意三点。 右闭左闭。
     * 1. 右闭，即最大的下标为length() - 1
     * 2. 因为最大length() - 1 ， 所以while循环的条件是 left <= right
     * 3. 如果二分查找，找出的middle比target大。 那么right = middle - 1
     * @param nums
     * @param target
     * @return
     */
    public static int binarySearch1(int[] nums, int target) {
        /* 下标 */
        int left = 0;
        int right = nums.length - 1;
        while(left <= right) {
            int middle = left + (right - left) / 2;
            if (target < nums[middle]) {
                right = middle - 1;
            } else if (target > nums[middle] ) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    /**
     * 第二种写法，即左闭右开
     * 1. 右开，即最大的下标length()
     * 2. 因为最大的下标为length()， 所以判断条件while判断条件是left < right
     * 3. 如果二分查找，找出来的middle比target大，那么右边就是middle
     * @param nums
     * @param target
     * @return
     */
    public static int binarySearch2(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while(left < right) {
            int middle = left + ((right - left) >> 1);
            if (target < nums[middle]) {
                right = middle;
            } else if (target > nums[middle]) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }


    /**
     * 二分查找 左闭右闭的写法
     * 二分查找的应用范围 - 有序数组
     *
     * @param nums
     * @param target
     * @return
     */
    public static int binarySearch3(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        // 循环条件
        while(left <= right) {
            int middle = left + ((right - left) >> 1);
            if (target > nums[middle]) {
                left = middle + 1;
            } else if (target < nums[middle]) {
                right = middle - 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    /**
     * 二分查找 左闭右闭的写法， 应用范围 有序数组
     * @param nums
     * @param target
     */
    public static int binarySearch4(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            // 找到中间点
            int middle = left + ((right - left) >> 1);
            if (target > nums[middle]) {
                left = middle + 1;
            } else if (target < nums[middle]) {
                right = middle - 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    /**
     * 二分查找
     * @param nums
     * @param target
     * @return
     */
    public static int binarySearch5(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid -1;
            } else {
                return mid;
            }
        }
        return -1;
    }

}
