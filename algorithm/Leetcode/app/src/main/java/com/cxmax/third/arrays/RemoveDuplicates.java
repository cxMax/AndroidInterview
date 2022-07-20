package com.cxmax.third.arrays;

/**
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 *
 * 26.删除有序数组中的重复项
 *
 * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
 *
 * 输出：5, nums = [0,1,2,3,4]
 *
 * 解释：函数应该返回新的长度 5
 *
 * Created by caixi on 2022/7/20.
 */
public class RemoveDuplicates {

    /**
     * 数组，去重复，
     * 还是跟移除元素类似，注意两点；
     *
     * 1.  前后进行比较
     * 2.  记得要从第1位开始进行比较(快、慢指针都要使用)
     * 3.  还是得使用双指针
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int slow = 1;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }
}
