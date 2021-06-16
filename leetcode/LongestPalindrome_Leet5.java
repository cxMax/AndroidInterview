package com.ludashi.dualspace.util;

import android.text.TextUtils;

/**
 * @describe :
 * @usage :
 * <p>
 *      输入：s = "babad"
 *      输出："bab"
 *      解释："aba" 同样是符合题意的答案。
 *
 *      最长回文数字， 正反读都是一样
 *
 * </p>
 * Created by caixi on 6/16/21.
 */
public class LongestPalindrome_Leet5 {

    public static void main(String[] args) {
        String s = "abcba";

    }


    /**
     * 暴力解法
     * 时间复杂度 O(n ^ 2)
     * 
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
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


}
