package com.cxmax.third.hash;

/**
 * 242. 有效的字母异位词
 *
 * https://leetcode-cn.com/problems/valid-anagram/
 *
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 *
 * Created by caixi on 2022/7/25.
 */
public class IsAnagram {

    /**
     * 统计频率， 我一来就想到了HashMap，
     *
     * 这道题的核心是用数组实现map
     *
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s == null && t == null) {
            return true;
        }
        // 异常判断
        if (s == null || t == null) {
            return false;
        }
        int[] ret = new int[26];
        for (int i = 0; i < s.length(); i++) {
            ret[s.charAt(i) - 'a'] += 1;
        }
        for (int i = 0; i < t.length(); i++) {
            ret[t.charAt(i) - 'a'] -= 1;
        }
        for (int i = 0; i < ret.length; i++) {
            if (ret[i] != 0) {
                return false;
            }
        }
        return true;
    }

}
