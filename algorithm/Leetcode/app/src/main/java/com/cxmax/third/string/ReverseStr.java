package com.cxmax.third.string;

/**
 * 541. 反转字符串 II
 * <p>
 * https://leetcode-cn.com/problems/reverse-string-ii/
 * <p>
 * Created by caixi on 2022/7/27.
 */
public class ReverseStr {

    /**
     * 每2k个字符，翻转前k个，
     *
     * 1. 两点，每2k个字符，就是+= 2k；
     * 2. 剩余部分，大于k 小于2k的，取剩余长度
     *
     * @param s
     * @param k
     * @return
     */
    public String reverseStr(String s, int k) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i += 2 * k) {
            // 确定起始点
            int left = i;
            // 这里是处理大于k，不足2k的处理
            int right = Math.min(chars.length - 1, i + k - 1);
            while (left < right) {
                char tmp = chars[left];
                chars[left] = chars[right];
                chars[right] = tmp;
                left++;
                right--;
            }
        }
        return new String(chars);

    }
}
