package com.cxmax.third.string;

/**
 * 剑指 Offer 05. 替换空格
 * <p>
 * https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/
 * <p>
 * Created by caixi on 2022/7/27.
 */
public class ReplaceSpace {

    /**
     * 把空格替换成一个字符
     *
     * 直接申请一个内存，去遍历替换
     *
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                sb.append("%20");
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();

    }

}
