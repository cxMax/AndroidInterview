package com.cxmax.selftest.string;

/**
 * 151. 翻转字符串里的单词
 * <p>
 * https://leetcode-cn.com/problems/reverse-words-in-a-string/
 * <p>
 * Created by caixi on 2022/2/25.
 */
public class ReverseWords {

    public String reverseWords(String s) {
        // 等到去掉空格的字符串
        StringBuilder sb = removeSpace(s);
        // 翻转所有的字母
        reverseString(sb, 0, sb.length() - 1);
        // 再反转每一个单词
        reverseEachWord(sb);
        return sb.toString();
    }

    /**
     * 去掉多余的空格
     * <p>
     * 使用双指针处理
     *
     * @param s
     * @return
     */
    private StringBuilder removeSpace(String s) {
        // 先去掉首、尾的空格
        int left = 0;
        int right = s.length() - 1;
        while (s.charAt(left) == ' ') {
            left++;
        }
        while (s.charAt(right) == ' ') {
            right--;
        }
        StringBuilder sb = new StringBuilder();
        while (right >= left) {
            if (s.charAt(left) != ' ' || sb.charAt(sb.length() - 1) != ' ') {
                sb.append(s.charAt(left));
            }
            left++;
        }
        return sb;
    }

    /**
     * 双指针
     * <p>
     * 纯反转字符串
     *
     * @param sb
     * @param left
     * @param right
     */
    public void reverseString(StringBuilder sb, int left, int right) {
        while (right > left) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, tmp);
            left++;
            right--;
        }
    }

    /**
     * 反转每一个单词
     *
     * @param sb
     */
    private void reverseEachWord(StringBuilder sb) {
        int left = 0;
        int right = 1;
        int len = sb.length();
        // 先确定第一个词
        while (left < len) {
            while (right < len && sb.charAt(right) != ' ') {
                right++;
            }
            reverseString(sb, left, right - 1);
            left = right + 1;
            right = left + 1;
        }
    }

}
