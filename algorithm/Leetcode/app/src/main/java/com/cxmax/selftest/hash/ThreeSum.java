package com.cxmax.selftest.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 *
 * https://leetcode-cn.com/problems/3sum/
 *
 * 是否存在三个元素 a，b，c ，使得 a + b + c = 0
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 *
 * Created by caixi on 2022/1/28.
 */
public class ThreeSum {

    /**
     * 使用双指针来解决这道题
     *
     * 看了一遍，不一定记得住
     * 
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // 先进行排序
        Arrays.sort(nums);
        // 进行异常判断处理
        for (int i = 0; i < nums.length; i++) {
            // 如果第一位大于0，后面永远相加不为0
            if (nums[i] > 0) {
                continue;
            }
            // 指针往前移动去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 这里开始双指针移动
            int left = i + 1;
            int right = nums.length - 1;
            while(left < right) {
                if (nums[i] + nums[left] + nums[right] > 0) {
                    right--;
                } else if (nums[i] + nums[left] + nums[right] < 0) {
                    left++;
                } else {
                    // 等于0的case
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 去重处理
                    while(right > left && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (right > left && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    // 找到后开始收缩指针
                    left++;
                    right--;
                }
            }
        }
        return result;
    }

}
