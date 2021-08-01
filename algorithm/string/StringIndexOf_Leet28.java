package com.android.test1.string;

/**
 * @describe :
 * @usage :
 * <p>
 * 28. 实现 strStr()
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：haystack = "hello", needle = "ll"
 * 输出：2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-strstr
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </p>
 * <p>
 * <p>
 * Created by caixi on 7/20/21.
 */
public class StringIndexOf_Leet28 {


    /**
     * 这个主要是KMP算法， 有个前缀表 和 next数组
     * KMP算法的时间复杂度是O(n+m）
     * 暴力的解法显而易见是O(n * m)
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        int[] next = new int[needle.length()];
        getNext(next, needle);
        int j = -1;
        for (int i = 0; i < haystack.length(); i++) {
            while(j >= 0 && haystack.charAt(i) != needle.charAt(j + 1)) {
                j = next[j];
            }
            // 如果出现相等的时候
            if (haystack.charAt(i) == needle.charAt(j + 1)) {
                j++;
            }
            if (j == needle.length() - 1) {
                return i - needle.length() + 1;
            }
        }
        return -1;
    }

    /**
     * 获取getNext数组，  也就是前缀表， 前缀表保存什么呢？记录下标i之前（包括i）的字符串中，有多大长度的相同前缀后缀
     *
     * @param next
     * @param s 模式串
     */
    public void getNext(int[] next, String s) {
        int j = -1;
        next[0] = j;
        for (int i = 0; i < s.length(); i++) {
            while(j >= 0 && s.charAt(j) != s.charAt(j + 1)) {
                j = next[j];
            }
            if (s.charAt(i) == s.charAt(j + 1)) {
                j++;
            }
            next[i] = j;
        }
    }
}
