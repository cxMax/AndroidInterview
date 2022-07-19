package com.cxmax.selftest.string;

/**
 * 剑指 Offer 05. 替换空格
 *
 * https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/
 *
 * Created by caixi on 2022/2/24.
 */
public class ReplaceSpace {

    /**
     * 再构造一个对象遇到空格的时候 append
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        // check null
        if (s == null || s.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                sb.append("%20");
                continue;
            }
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

}
