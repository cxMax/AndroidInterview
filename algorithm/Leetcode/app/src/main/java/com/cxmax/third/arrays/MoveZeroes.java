package com.cxmax.third.arrays;

/**
 * 283. 移动零
 *
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 输入: [0,1,0,3,12]
 *
 * 输出: [1,3,12,0,0]
 *
 * Created by caixi on 2022/7/19.
 */
public class MoveZeroes {

    /**
     * 感觉这道题，跟移除元素一样，只是多了一步把末尾加个0
     *
     * 1. 因此需要用到双指针，数组移除元素使用
     *
     * 2. 需要用到count进行记数
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int slow = 0;
        int count = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            } else {
                count++;
            }
        }
        // 从末尾按照count数量进行赋值0
        for (int i = 0; i < count; i++) {
            nums[nums.length - i -1] = 0;
        }
    }

}
