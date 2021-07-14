package com.android.test1;

/**
 * @describe :
 * @usage :
 * <p>
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 *
 * 进阶：
 *
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </p>
 * Created by caixi on 6/24/21.
 */
public class SearchRange_Leet34 {

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return buildErrorArray();
        }
        int left = 0;
        int right = nums.length - 1;
        int ans = -1;
        while(left <= right) {
            int middle = left + ((right - left) >> 1);
            if (target > nums[middle]) {
                left = middle + 1;
            } else if (target < nums[middle]) {
                right = middle - 1;
            } else {
                ans = middle;
                break;
            }
        }
        if (ans == -1) {
            return buildErrorArray();
        }
        int start = ans;
        while(start > 0 && nums[start - 1] == target) {
            start--;
        }
        int end = ans;
        while(end < nums.length - 1 && nums[end + 1] == target) {
            end++;
        }
        return new int[]{start, end};
    }



    private int[] buildErrorArray() {
        return new int[]{-1, -1};
    }
}
