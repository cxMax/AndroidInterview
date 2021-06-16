package com.ludashi.dualspace.util;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * @describe :
 * @usage :
 * <p>
 *      输入: s = "abcabcbb"
 *      输出: 3
 *      解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * </p>
 * Created by caixi on 6/15/21.
 */
public class LengthOfLongestSubstring_Leet3 {

    public static void main(String[] args) {
        String s  = "abcabcbb";
        int ret = lengthOfLongestSubstring(s);
        Log.e("info", "main: ret = " + ret );
    }

    private static int lengthOfLongestSubstring(String s) {
        int ans = 0;
        Map<Character, Integer> characters = new HashMap<>();
        for (int end = 0, start = 0; end < s.length(); end++) {
            char c =  s.charAt(end);
            if (characters.containsKey(c)) {
                // 更新 start
                start = Math.max(characters.get(c), start);
            }
            // 更新结果answer
            ans = Math.max(ans, end - start + 1);
            // 把所有字符放到map中
            characters.put(c, end + 1);
        }

        return ans;
    }

}
