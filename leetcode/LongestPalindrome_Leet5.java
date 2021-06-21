package com.ludashi.dualspace.util;

import android.text.TextUtils;

/**
 * @describe :
 * @usage :
 * <p>
 *      输入：s = "babad"
 *      输出："bab"
 *      解释："aba" 同样是符合题意的答案。
 * <p>
 * 最长回文数字， 正反读都是一样
 *
 * </p>
 * Created by caixi on 6/16/21.
 */
public class LongestPalindrome_Leet5 {

    public static void main(String[] args) {
        String s = "abcba";

    }


    /**
     * 暴力解法 , 这个不得行， 根本通不过
     * 时间复杂度 O(n ^ 3) 三次for循环
     *
     * @param s
     * @return
     */
    public static String longestPalindrome1(String s) {
        String result = "";
        if (s == null || s.length() == 0) {
            return result;
        }
        int max = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; j++) {
                String tmp = s.substring(i, j);
                if (isPalindromic(tmp) && tmp.length() > max) {
                    result = tmp;
                    max = Math.max(max, tmp.length());
                }
            }

        }
        return result;
    }


    /**
     * 判断字符串是不是回文数字
     *
     * @param s
     * @return
     */
    public static boolean isPalindromic(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 中心扩散法， 理解到位的做法
     * @param s
     * @return
     */
    public static String longestPalindrome2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        String result = "";
        for (int i = 0; i < 2 * len - 1; i++) {
            int left = i / 2;
            int right = left + i % 2;
            while(left >= 0 && right < len && (s.charAt(left) == s.charAt(right))) {
                String tmp = s.substring(left, right + 1);
                if (tmp.length() > result.length()) {
                    result = tmp;
                }
                left--;
                right++;
            }
        }
        return result;
    }

    /**
     * 中心扩散法， 目前能够理解的也只有中心扩散算法了
     * @param s
     * @return
     */
    public static String longestPalindrome1(String s) {
        if (s == null || s.length() == 0) {
            return ""
        }
        if (s.length() < 2) {
            return s;
        }
        // 中心扩散法，定义最大长度， 和角标数组, 第一位记录起始位置， 第二位记录长度
        int[] ret = new int[2];
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            // 奇数
            int[] odd = centerSpread(s, i, i);
            // 偶数
            int[] even = centerSpread(s, i, i + 1);
            int[] max = odd[1] > even[1] ? odd[1] : even[1];
            if (max[1] > maxLen) {
                ret = max;
                maxLen = max[1];
            }
        }
        return s.substring(ret[0], ret[0] + ret[1]);
    }

    /**
     * 中心扩散法
     * todo 这里学会了一个方法， 就是验证最后一步，直接用结论去套
     * @param s
     * @param left
     * @param right
     * @return 返回的是，最长回文字串的下标
     */
    private static int[] centerSpread(String s, int left, int right) {
        int len = s.length();
        while(left >=0 && right < len) {
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            } else {
                break;
            }
        }
        return new int[]{left, right - left - 1}; // 这里返回字符串的起始下标, 这里直接想象 abba  这个场景怎么返回的
    }


}
