package com.cxmax.selftest.arrays;

import java.util.Stack;

/**
 *
 * 844. 比较含退格的字符串
 *
 * https://leetcode-cn.com/problems/backspace-string-compare/
 *
 * 输入：s = "ab#c", t = "ad#c"
 *
 * 输出：true
 *
 * 解释：S 和 T 都会变成 “ac”。
 *
 *
 * Created by caixi on 2022/1/10.
 */
public class BackspaceCompare {

    public boolean backspaceCompare(String s, String t) {
        return build(s).equals(build(t));
    }

    /**
     * 第一种方式， 是使用stack特性来做， 进栈、出栈来做
     * @param str
     * @return 返回退格后，做比较的字符串
     */
    public Stack<Character> build(String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
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

    /**
     * 第二种方式，使用双指针来做这道题
     *
     * 1.从最后一位往前遍历，如果是'#', 就ship++,
     * 2.最后看，是否同时移动至0，如果期间有每次移动值不相等的地方， 那都不相等
     *
     * @param s
     * @param t
     * @return
     */
    public boolean backspaceCompare1(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;
        int skipS = 0;
        int skipT = 0;
        while(i >=0 || j >= 0) {
            // 先都往后退
            while(i >= 0) {
                if (s.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else {
                    break;
                }
            }
            // 先都往后退
            while(j >= 0) {
                if (t.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break;
                }
            }
            // 这会开始比较两个值是否相等
            if (i >= 0 && j >= 0) {
                if (s.charAt(i) != t.charAt(j)) {
                    return false;
                }
            } else {
                // 如果这个时候，已经有一个先走到头了
                if (i >=0 || j >= 0) {
                    return false;
                }
            }
            // 然后相等，就继续--
            i--;
            j--;

        }
        return true;
    }

}
