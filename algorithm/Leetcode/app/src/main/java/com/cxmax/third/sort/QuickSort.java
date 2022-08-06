package com.cxmax.third.sort;

/**
 * 912. 排序数组
 * <p>
 * https://leetcode.cn/problems/sort-an-array/
 * <p>
 * Created by caixi on 2022/8/6.
 */
public class QuickSort {


    /**
     * 快速排序， 我初看，有点像二分查找
     * 时间复杂度o(nlogn)
     *
     * @param nums
     * @return
     */
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        // todo caixi 2022-8-6 快排 这里high位要写成length - 1
        sortArray(nums, 0, nums.length - 1);
        return nums;
    }

    private void sortArray(int[] nums, int low, int high) {
        if (low < high) {
            // 查找中间位置
            int mid = getMiddle(nums, low, high);
            // 递归，排序低位
            sortArray(nums, low, mid - 1);
            // 递归，排序高位
            sortArray(nums, mid + 1, high);
        }

    }

    private int getMiddle(int[] nums, int low, int high) {
        int tmp = nums[low];
        while(low < high) {
            // 高位--
            while(low < high && nums[high] >= tmp) {
                high--;
            }
            // 交换位置
            nums[low] = nums[high];
            // 低位++
            while(low < high && nums[low] <= tmp) {
                low++;
            }
            // 交换位置
            nums[high] = nums[low];
        }
        // 还原
        nums[low] =  tmp;
        return low;
    }

}
