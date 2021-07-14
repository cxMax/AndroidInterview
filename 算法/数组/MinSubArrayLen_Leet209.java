package com.android.test1;

/**
 * @describe :
 * @usage :
 * <p>
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * </p>
 * Created by caixi on 7/14/21.
 */
public class MinSubArrayLen_Leet209 {

    /**
     * 暴力解法
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        // 子序列的长度
        int result = Integer.MAX_VALUE;
        // 子序列
        int sum = 0;
        // 子序列的长度
        int subLength = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= target) {
                    subLength = j - i + 1;
                    result = Math.min(result, subLength);
                    break;
                }

            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    /**
     * 滑动窗口， 双指针
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen1(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        // 子序列的长度
        int result = Integer.MAX_VALUE;
        // 子序列
        int sum = 0;
        // 子序列的长度
        int subLength = 0;
        int i = 0;
        for (int j = 0; j < len; j++) {
            sum += nums[j];
            while(sum >= target) {
                subLength = j - i + 1;
                result = Math.min(result, subLength);
                sum -= nums[i++];
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
