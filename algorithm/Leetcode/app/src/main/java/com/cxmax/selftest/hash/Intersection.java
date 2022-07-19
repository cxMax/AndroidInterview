package com.cxmax.selftest.hash;

import java.util.HashSet;

/**
 *
 * 349. 两个数组的交集
 *
 * https://leetcode-cn.com/problems/intersection-of-two-arrays/
 *
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 *
 * 输出：[9,4]
 *
 * Created by caixi on 2022/1/27.
 */
public class Intersection {

    /**
     * 判断两个数组之间的交集， 使用hashset来做， HashSet主要还有去重
     *
     * 就是一个很简单的逻辑题了
     *
     * 1. a数组元素放入hashSet
     * 2. b数组元素包含a数组元素的部分放入hashset
     * 3. 将hashset转化成数组进行输出
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> result = new HashSet<>();
        // 遍历集合1
        for (int i : nums1) {
            set1.add(i);
        }
        // 取出公共值
        for (int i : nums2) {
            if (set1.contains(i)) {
                result.add(i);
            }
        }
        // 输出成数组
        int[] ret = new int[result.size()];
        int index = 0;
        for (int i : result) {
            ret[index] = i;
            index++;
        }
        return ret;
    }

}
