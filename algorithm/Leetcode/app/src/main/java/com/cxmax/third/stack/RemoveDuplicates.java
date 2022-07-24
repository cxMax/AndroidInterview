package com.cxmax.third.stack;

import java.util.Stack;

/**
 * 1047. 删除字符串中的所有相邻重复项
 * <p>
 * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。在 S 上反复执行重复项删除操作，直到无法继续删除。
 * <p>
 * "abbaca" -> 删除"bb" -> aaca -> 删除"aa" -> ca
 * <p>
 * Created by caixi on 2022/7/24.
 */
public class RemoveDuplicates {

    /**
     * 去掉相邻的重复项，确实，用栈来做就很方便；
     *
     * @param s
     * @return
     */
    public String removeDuplicates(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.isEmpty() || stack.peek() != c) {
                stack.push(c);
            } else {
                stack.pop();
            }
        }
        String ret = "";
        for (Character c : stack) {
            ret += c;
        }
        return ret;

    }
}
