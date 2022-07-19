package com.cxmax.selftest.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/valid-parentheses/
 * <p>
 * 20. 有效的括号
 * <p>
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * <p>
 * 输入：s = "([)]"
 * 输出：false
 * Created by caixi on 2022/3/3.
 */
public class IsValid {

    /**
     * 这个题给我的感觉， 有点像，手写json解析
     *
     * 因为元素是有序且匹配的，考虑用栈来做，进栈出栈来做
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        // 用LinkedList来做栈
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '(') {
                stack.push(')');
            } else if (stack.isEmpty() || stack.peek() != c) {
                return false;
            } else {
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

}
