package com.cxmax.selftest.string;

/**
 * https://leetcode-cn.com/problems/implement-strstr/
 * <p>
 * 28. 实现 strStr()
 * <p>
 * 输入：haystack = "hello", needle = "ll"
 * <p>
 * 输出：2
 * <p>
 * 其实这道题就是String.indexOf("")这个算法
 * <p>
 * Created by caixi on 2022/3/1.
 */
public class StrStr {

    /**
     * 滑动窗口算法，KMP算法
     * 其实也就是indexOf这个函数
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

}
