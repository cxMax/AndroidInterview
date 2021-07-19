package com.android.test1.string;

/**
 * @describe :
 * @usage :
 * <p>
 * 剑指 Offer 58 - II. 左旋转字符串
 * 输入: s = "abcdefg", k = 2
 * 输出: "cdefgab"
 * </p>
 * Created by caixi on 7/19/21.
 */
public class ReverseLeftWords_Offer58 {

    public String reverseLeftWords(String s, int n) {
        StringBuilder sb = new StringBuilder(s);
        reverseString(sb, 0, n - 1);
        reverseString(sb, n, s.length() - 1);
        reverseString(sb, 0, s.length() - 1);
        return sb.toString();
    }

    /**
     * 反转字符串
     * @param sb
     * @param left
     * @param right
     */
    public void reverseString(StringBuilder sb, int left, int right) {
        while(right > left) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, tmp);
            left++;
        }
    }

}
