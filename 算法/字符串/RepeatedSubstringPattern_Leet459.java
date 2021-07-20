package com.android.test1.string;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 7/20/21.
 */
public class RepeatedSubstringPattern_Leet459 {

    public boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        int[] next = new int[s.length()];
        getNext(next, s);
        int len = s.length();
        if (next[len - 1] != 0 && len % (len - next[len - 1]) == 0) {
            return true;
        }
        return false;
    }


    /**
     * 获取next数组， 这个是前缀表
     * @param next
     * @param s
     */
    public void getNext(int[] next, String s) {
        int j = 0;
        next[j] = 0;
        for (int i = 1; i < s.length(); i++) {
            // 不同的情况下
            while(j > 0 && s.charAt(i) != s.charAt(j)) {
                j = next[j - 1];
            }
            // 相同的情况
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
    }
}
