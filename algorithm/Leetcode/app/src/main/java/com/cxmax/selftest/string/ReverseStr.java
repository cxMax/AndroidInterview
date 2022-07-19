package com.cxmax.selftest.string;

/**
 * 541. 反转字符串 II
 * <p>
 * https://leetcode-cn.com/problems/reverse-string-ii/
 * <p>
 * <p>
 * Created by caixi on 2022/2/22.
 */
public class ReverseStr {

    /**
     * 前2k个字符，翻转前k个字符
     *
     * @param s
     * @param k
     * @return
     */
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i += (2 * k)) {
            // 这里理解，第一个2k， 要翻转前k个
            int left = i;
            int right = Math.min(chars.length - 1, i + k - 1);
            while(right > left) {
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
