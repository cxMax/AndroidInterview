package com.cxmax.selftest.arrays;

/**
 *
 * 283. 移动零
 *
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 输入: [0,1,0,3,12]
 *
 * 输出: [1,3,12,0,0]
 *
 * Created by caixi on 2022/1/5.
 */
public class MoveZeroes {

    /**
     * 这道题双指针来做，在引入一个新的count来统计0的变量
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int slow = 0;
        int count = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if(nums[fast] != 0) {
                nums[slow++] = nums[fast];
            } else {
                count++;
            }
        }
        for (int i = 0; i < count; i++) {
            nums[nums.length - 1 - i] = 0;
        }
    }

}
