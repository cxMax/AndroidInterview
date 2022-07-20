package com.cxmax.third.arrays;

/**
 *
 * https://leetcode.cn/problems/search-insert-position/
 *
 * 输入: nums = [1,3,5,6], target = 5
 *
 * 输出: 2
 *
 * Created by caixi on 2022/7/20.
 */
public class SearchInsert {

    /**
     * 这个题，跟二分查找类似；
     *
     * 只是记住一点，二分查找是返回目标元素的位置，也就是mid
     *
     * 这个查找搜索位置，返回的是，目标位置的左边节点，也就是left
     *
     * 左闭右闭写法
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        // todo caixi 2022-7-20 第一个地方写错了，是异常判断，如果没查找到要返回-1
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while(left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                // todo caixi 2022-7-20 第二个写错了的地方，是这里，如果查找到等于目标位置，直接插入位置返回当前的位置
                return mid;
            }
        }
        return left;
    }

}
