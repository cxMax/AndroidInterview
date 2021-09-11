package com.cxmax.leetcode.arrays;

import java.util.Arrays;

/**
 * @describe :
 * @usage :
 * <p>
 * 输入：nums = [-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 解释：平方后，数组变为 [16,1,0,9,100]
 * 排序后，数组变为 [0,1,9,16,100]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/squares-of-a-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </p>
 * Created by caixi on 7/14/21.
 */
public class SortedSquares_Leet977 {

    /**
     * 常规的暴力做法， 先乘积，再排序
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] *= nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }

    /**
     * 双指针做法， new一个数组，
     * @param nums
     * @return
     */
    public int[] sortedSquares2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int k = nums.length - 1;
        int[] result = new int[nums.length];
        for (int i = 0, j = nums.length - 1; i <= j;) {
            if (nums[i] * nums[i] < nums[j] * nums[j]) {
                result[k--] = nums[j] * nums[j];
                j--;
            } else {
                result[k--] = nums[j] * nums[j];
                i++;
            }
        }
        return result;
    }

    /**
     * 有序数组的平方，排序
     * 双指针，首尾比较
     * @param nums
     * @return
     */
    public int[] sortedSquares3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int slow = 0;
        int fast = nums.length - 1;
        int[] result = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            int retSlow = nums[slow] * nums[slow];
            int retFast = nums[fast] * nums[fast];
            if (retSlow > retFast) {
                result[i] = retSlow;
                slow++;
            } else {
                result[i] = retFast;
                fast--;
            }
        }
        return result;
    }
}
