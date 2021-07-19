package com.android.test1.string;

/**
 * @describe :
 * @usage :
 * <p>
 * 输入：s = "  hello world  "
 * 输出："world hello"
 * </p>
 * Created by caixi on 7/19/21.
 */
public class ReverseWords_Leet151 {

    public String reverseWords(String s) {
        // 先去掉多余的空格
        StringBuilder sb = removeSpace(s);
        // 先反转整个字符串
        reverseString(sb, 0, sb.length() - 1);
        // 再反转每一个单词
        reverseEachWord(sb);
        return sb.toString();
    }

    /**
     * 去掉多余的空格 (包括首、尾， 和中间部分)
     *
     * @param s
     * @return
     */
    private StringBuilder removeSpace(String s) {
        // 先去掉首、尾多余的空格
        int left = 0;
        int right = s.length() - 1;
        while(s.charAt(left) == ' ') {
            left++;
        }
        while(s.charAt(right) == ' ') {
            right--;
        }
        StringBuilder sb = new StringBuilder();
        while(right >= left) {
            //
            if (s.charAt(left) != ' ' || sb.charAt(sb.length() - 1) != ' ') {
                sb.append(s.charAt(left));
            }
            left++;
        }
        return sb;


    }

    /**
     * 反转字符串
     *
     * @param sb
     * @param left
     * @param right
     */
    public void reverseString(StringBuilder sb, int left, int right) {
        // 还是双指针来做
        while(right > left) {
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
        while(left < len) {
            while(right < len && sb.charAt(right) != ' ') {
                right++;
            }
            reverseString(sb, left, right - 1);
            left = right + 1;
            right = left + 1;
        }
    }
}
