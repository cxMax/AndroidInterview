package com.cxmax.third.string;

/**
 * 151. 翻转字符串里的单词
 * <p>
 * https://leetcode-cn.com/problems/reverse-words-in-a-string/
 * <p>
 * Created by caixi on 2022/7/27.
 */
public class ReverseWords {


    /**
     *
     * 这道题固定算法，"the sky blue "为例
     *
     * 1. 先去除首位空格 "the sky blue"
     * 2. 再反转字符串, "eulb yks eht"， 双指针
     * 3. 再反转每一个单词, "blue sky the"
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        // 去掉空格
        String newS = removeSpace(s);
        // 反转所有字符串
        String reverse = reverseString(newS, 0, newS.length() - 1);
        // 反转每一个单词
        StringBuilder sb = new StringBuilder(reverse);
        int left = 0;
        int right = 1;
        int len = sb.length();
        // 先确定第一个词
        while (left < len) {
            while (right < len && sb.charAt(right) != ' ') {
                right++;
            }
            reverseString(sb.toString(), left, right - 1);
            left = right + 1;
            right = left + 1;
        }

        return sb.toString();


    }

    /**
     * 先去除空格
     *
     * @param s
     * @return
     */
    private String removeSpace(String s) {
        int left = 0;
        int right = s.length() - 1;
        while(s.charAt(left) == ' ') {
            left++;
        }
        while(s.charAt(right) == ' ') {
            right--;
        }
        return s.substring(left, right);
    }


    /**
     * 翻转字符串
     *
     * @param s
     * @param start
     * @param end
     * @return
     */
    private String reverseString(String s, int start, int end) {
        char[] chars = s.toCharArray();
        int left = start;
        int right = end;
        while(left < right) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
            left++;
            right--;
        }
        return new String(chars);
    }

}
