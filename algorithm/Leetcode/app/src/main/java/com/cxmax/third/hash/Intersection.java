package com.cxmax.third.hash;

import java.util.HashSet;

/**
 * 349. 两个数组的交集
 * <p>
 * https://leetcode-cn.com/problems/intersection-of-two-arrays/
 * <p>
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * <p>
 * 输出：[9,4]
 * <p>
 * Created by caixi on 2022/7/26.
 */
public class Intersection {

    /**
     * 判断两个数组之间的交集， 使用hashset来做， HashSet主要还有去重
     * <p>
     * 就是一个很简单的逻辑题了
     * <p>
     * 1. a数组元素放入hashSet
     * 2. b数组元素包含a数组元素的部分放入hashset
     * 3. 将hashset转化成数组进行输出
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        // 异常判断
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        // 定义两个HashSet
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> intersectionSet = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set1.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (set1.contains(nums2[i])) {
                intersectionSet.add(nums2[i]);
            }
        }
        // 遍历，输出结果
        int[] ret = new int[intersectionSet.size()];
        int i = 0;
        for (Integer num : intersectionSet) {
            ret[i] = num;
            i++;
        }
        return ret;
    }
}
