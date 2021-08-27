package com.cxmax.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @describe :
 * @usage :
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </p>
 * Created by caixi on 7/21/21.
 */
public class TwoSum_Leet1 {

    /**
     * 这道题其实就是，通过HashMap来做，但是有一点，不能为重复元素
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        // 第一位数字， 第二位角标
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            int num = target - cur;
            if (map.containsKey(num)) {
                result[0] = i;
                result[1] = map.get(num);
            }
            map.put(cur, i);
        }
        return result;
    }
}
