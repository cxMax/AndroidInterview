package com.cxmax.selftest.hash;

/**
 *
 * 242. 有效的字母异位词
 *
 * https://leetcode-cn.com/problems/valid-anagram/
 *
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 *
 * Created by caixi on 2022/1/25.
 */
public class IsAnagram {

    /**
     * 没有直接使用hashmap的数据结构
     *
     * 使用数组来做hashmap
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return false;
        }
        int[] record = new int[26];
        for (int i = 0; i < s.length(); i++) {
            record[s.charAt(i) - 'a'] += 1;
        }
        for (int i = 0; i < t.length(); i++) {
            record[t.charAt(i) - 'a'] -= 1;
        }

        for (int i = 0; i < record.length; i++) {
            if (record[i] != 0) {
                return false;
            }
        }
        return true;
    }

}
