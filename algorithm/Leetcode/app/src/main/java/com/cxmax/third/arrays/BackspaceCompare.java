package com.cxmax.third.arrays;

import java.util.Stack;

/**
 * 844. 比较含退格的字符串
 * <p>
 * https://leetcode-cn.com/problems/backspace-string-compare/
 * <p>
 * 输入：s = "ab#c", t = "ad#c"
 * <p>
 * 输出：true
 * <p>
 * 解释：S 和 T 都会变成 “ac”。
 * <p>
 * Created by caixi on 2022/7/20.
 */
public class BackspaceCompare {


    /**
     * 回退，这个我第一时间就想到了stack
     *
     * 遇到字符就出栈，没遇到就进栈
     *
     * @param s
     * @param t
     * @return
     */
    public boolean backspaceCompare(String s, String t) {
        return build(s).equals(build(t));
    }

    private Stack<Character> build(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != '#') {
                stack.push(c);
            } else {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }
        }
        return stack;
    }


}
