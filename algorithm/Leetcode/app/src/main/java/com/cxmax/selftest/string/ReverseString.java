package com.cxmax.selftest.string;

/**
 *
 * 344. 反转字符串
 *
 * https://leetcode-cn.com/problems/reverse-string/
 *
 * Created by caixi on 2022/2/22.
 */
public class ReverseString {

    /**
     *
     * 交换字符串，双指针大法
     *
     * 首尾元素相交换
     *
     * @param s
     */
    public void reverseString(char[] s) {
        // 1. null pointer check
        if (s == null || s.length == 0) {
            return;
        }
        int left = 0;
        int right = s.length - 1;
        while(left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

}
