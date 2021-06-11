package com.ludashi.dualspace.util;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * @describe :
 * @usage :
 * <p>
 *      两数求和 :
 *
 *      输入：nums = [2,7,11,15], target = 9
 *      输出：[0,1]
 *      解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 *
 * </p>
 * Created by caixi on 6/11/21.
 */
public class TwoSum_Leet1 {

    public static void main(String[] args) {
        int[] nums = new int[]{2,7,11,15};
        int target = 9;
        twoSum(nums, target);
    }

    /**
     * 常规解法， 暴力解法， 遍历列表
     * 时间复杂度 n(n^2) , 空间复杂度 O(1)
     * @param nums
     * @param target
     * @return
     */
    private static int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if(nums[i] + nums[j] == target) {
                    Log.e("info", "twoSum1: i = " + i + ", j = " + j);
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    /**
     * 通过HashMap containsKey
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> container = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (container.containsKey(target - nums[i])) {
                int j = container.get(target - nums[i]);
                Log.e("info", "twoSum1: i = " + i + ", j = " + j);
                return new int[]{j, i};
            }
            container.put(nums[i], i);
        }

        return new int[0];

    }


}
