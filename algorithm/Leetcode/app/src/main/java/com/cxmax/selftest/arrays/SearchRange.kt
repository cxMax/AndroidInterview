package com.cxmax.selftest.arrays

/**
 *
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
 * Created by caixi on 2021/12/28.
 */
object SearchRange {

    fun test() {
        val array = arrayOf(0, 1, 2, 2, 3, 3, 4, 5)
        val target = 3
        searchRange(array, target)
    }

    fun searchRange(nums: Array<Int>, target: Int): Array<Int> {
        val index = binarySearch(nums, target)
        // 如果为 -1 就表示这个数列，没有找到
        if (index == -1) {
            return arrayOf(-1, -1)
        }
        var left = index
        var right = index
        while(left - 1 >= 0 && nums[left - 1] == target) {
            left--
        }
        while(right + 1 < nums.size && nums[right + 1] == target) {
            right++
        }
        return arrayOf(left, right)
    }

    private fun binarySearch(nums: Array<Int>?, target: Int): Int {
        if(nums == null || nums.size == 0) {
            return -1
        }
        var left = 0
        var right = nums.size - 1
        while(left <= right) {
            var middle = left + ((right - left) / 2)
            if (nums[middle] < target) {
                left = middle + 1
            } else if (nums[middle] > target) {
                right = middle - 1
            } else {
                return middle
            }
        }
        return -1
    }
}