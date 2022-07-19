package com.cxmax.selftest.string;

/**
 * 剑指 Offer 58 - II. 左旋转字符串
 * <p>
 * https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/
 * <p>
 * 输入: s = "abcdefg", k = 2
 * <p>
 * 输出: "cdefgab"
 * <p>
 * Created by caixi on 2022/2/28.
 */
public class ReverseLeftWords {


    /**
     * 这个题，固定算法：
     * <p>
     * 1. 反转前n
     * <p>
     * 2. 反转n， 到末尾
     * <p>
     * 3. 整体反转
     *
     * @param s
     * @param n
     * @return
     */
    public String reverseLeftWords(String s, int n) {
        StringBuilder sb = new StringBuilder(s);
        reverseString(sb, 0, n - 1);
        reverseString(sb, n, s.length() - 1);
        return sb.reverse().toString();
    }

    public void reverseString(StringBuilder sb, int left, int right) {
        while(right > left) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, tmp);
            left++;
            right--;
        }
    }

}
