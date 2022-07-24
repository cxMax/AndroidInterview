package com.cxmax.third.stack;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/valid-parentheses/
 * <p>
 * 20. 有效的括号
 * <p>
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * <p>
 * 输入：s = "([)]"
 * 输出：false
 * Created by caixi on 2022/7/24.
 */
public class IsValid {

    /**
     * 还是跟手写json一样，
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '(') {
                stack.push(')');
                // todo caixi 2022-7-24 第一个问题，在遍历过程中，如果stack空了，异常
                // todo caixi 2022-7-24 第二个问题，[](){}都不与当前字符相等，就是出现了其他字符，异常
            } else if (stack.isEmpty() || stack.peek() != c) {
                // todo caixi 2022-7-24 第三个问题，这里就直接返回异常了
                return false;
            } else {
                stack.pop();
            }
        }
        // todo caixi 2022-7-24 最后是否有效，判断栈是否是空的
        return stack.isEmpty();

    }
}
