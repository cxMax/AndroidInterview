package com.android.test1.node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @describe :
 * @usage :
 * <p>
 * 15. 三数之和
 * </p>
 * Created by caixi on 7/19/21.
 */
public class ThreeSum_Leet15 {

    /**
     * 双指针法来解决这个问题
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // 先排序
        Arrays.sort(nums);
        // 开始双指针遍历
        for (int i = 0; i < nums.length; i++) {
            // 如果第一位就大于0， 那么后面相加永远不可能等于0
            if (nums[i] > 0) {
                continue;
            }
            // 指针往前移动， 去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            // 双指针开始移动， 获取三个数之和相加等于0的数据
            while(right > left) {
                if (nums[i] + nums[left] + nums[right] > 0) {
                    right--;
                } else if (nums[i] + nums[left] + nums[right] < 0) {
                    left++;
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 这个时候，考虑去重的问题
                    while (right > left && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (right > left && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    // 无论怎么样，收缩左右两个指针
                    left++;
                    right--;
                }
            }
        }
        return result;
    }
}
