package com.ludashi.dualspace;

/**
 * @describe :
 * @usage :
 * <p>
 *     二分查找 , 返回元素下标
 * </p>
 * Created by caixi on 12/22/20.
 */
public class Binary_Search {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6};
        int target = 4;
        int index = binarySearch(nums, target);
        System.out.println(index);

    }

    public static int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        return -1;
    }
}
