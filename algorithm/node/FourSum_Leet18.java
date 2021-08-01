package com.android.test1.node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @describe :
 * @usage :
 * <p>
 * 18. 四数之和
 * 同样是双指针， 只是比起三数之和， 多了一个循环嵌套
 * </p>
 * Created by caixi on 7/19/21.
 */
public class FourSum_Leet18 {


    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // 还是先排序
        Arrays.sort(nums);
        // 开始遍历
        for (int i = 0; i < nums.length; i++) {
            // 还是考虑重复的问题
            if (i > 0 && nums[i] == nums[i -1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                // 同样考虑重复的问题
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                // 开始双指针遍历
                int left = j + 1;
                int right = nums.length - 1;
                while(right > left) {
                    if (nums[i] + nums[j] + nums[left] + nums[right] > target) {
                        right--;
                    } else if (nums[i] + nums[j] + nums[left] + nums[right] < target) {
                        left++;
                    } else {
                        // 先添加结果
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        // 考虑重复的问题
                        while (right > left && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        while(right > left && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        // 无论怎么样， 先缩短指针
                        left--;
                        right++;
                    }
                }
            }
        }
        return result;
    }
}
