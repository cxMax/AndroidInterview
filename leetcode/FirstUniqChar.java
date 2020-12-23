package com.art.editor.editor;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * @describe :
 *
 * @usage :
 * <p>
 *     字符串中，第一次出现不重复的char
 *     用 HashMap 或者 indexOf 或者 lastIndexOf来做
 * </p>
 * Created by caixi on 12/23/20.
 */
public class FirstUniqChar {

    public static void main(String[] args) {
        String s = "loveleetcode";
        Log.e("info", "main: " + firstUniqChar(s));
    }

    public static int firstUniqChar(String s) {
        if (s.length() == 0) {
            return -1;
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.indexOf(s.charAt(i)) == s.lastIndexOf(s.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 用HashMap来做
     * @param s
     * @return
     */
    public static int firstUniqChar1(String s) {
        if (s.length() == 0) {
            return -1;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }



}
