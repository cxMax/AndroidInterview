package com.android.test1;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * @describe :
 * @usage :
 * <p>
 *      题解 ： https://leetcode-cn.com/problems/palindromic-substrings/solution/liang-dao-hui-wen-zi-chuan-de-jie-fa-xiang-jie-zho/
 *      输入："aaa"
 *      输出：6
 *      解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 * </p>
 * Created by caixi on 6/21/21.
 */
public class CountSubstrings_Leet647 {

    public static void main(String[] args) {
        String s = "acabbac";
        int count = countSubstrings(s);
        int count1 = countSubstrings1(s);
        Log.e("info", "main: count = " + count + ", count1 = " + count1);
    }


    /**
     * 动态规划，做这个问题
     * @param s
     * @return
     */
    public static int countSubstrings(String s) {
        // 动态规划法
        boolean[][] dp = new boolean[s.length()][s.length()];
        int ans = 0;

        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    ans++;
                }
                Log.e("info", "countSubstrings: j = " + j + ", i = " + i + ", result = " + dp[i][j]);
            }
        }
        return ans;
    }


    /**
     * 中心扩散法，做这个问题
     * @param s
     * @return
     */
    public static int countSubstrings1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int len = s.length();
        int ans = 0;
        for (int i = 0; i < 2 * len - 1; i++) {
            int left = i / 2;
            int right = left + i % 2;
            while(left >= 0 && right < len && (s.charAt(left) == s.charAt(right))) {
                left--;
                right++;
                ans++;
            }
        }

        return ans;

    }



}
