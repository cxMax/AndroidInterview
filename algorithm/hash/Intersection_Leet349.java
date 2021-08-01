package com.android.test1.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * @describe :
 * @usage :
 * <p>
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * 示例 2：
 * <p>
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </p>
 * Created by caixi on 7/21/21.
 */
public class Intersection_Leet349 {

    /**
     * 求交集数字
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        Set<Integer> source1 = new HashSet<>();
        Set<Integer> result = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            source1.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (source1.contains(nums2[i])) {
                result.add(nums2[i]);
            }
        }
        int[] resultArray = new int[result.size()];
        int index = 0;
        for (Integer i : result) {
            resultArray[index++] = i;
        }
        return resultArray;
    }

}
