package com.cxmax.selftest.arrays;

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
 * Created by caixi on 2022/1/5.
 */
public class RemoveDuplicates {

    /**
     * 这个问题， 我第一想到的就是双指针
     * 去重，我又想到了HashMap
     *
     * 有序数组去重，那就是前后不想等，慢指针移动
     *
     * 这道题还有个细节点啊， 就是要从第1位开始移动。 因为第1位，永远不重复
     *
     * @param nums
     * @return Int
     */
    public int removeDuplicates(int[] nums) {
        int slow = 1;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow++] = nums[fast];
            }
        }
        return slow;

    }

}
