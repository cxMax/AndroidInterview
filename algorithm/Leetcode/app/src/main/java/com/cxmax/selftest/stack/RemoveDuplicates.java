package com.cxmax.selftest.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 1047. 删除字符串中的所有相邻重复项
 * <p>
 * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。在 S 上反复执行重复项删除操作，直到无法继续删除。
 * <p>
 * "abbaca" -> 删除"bb" -> aaca -> 删除"aa" -> ca
 * <p>
 * Created by caixi on 2022/3/4.
 */
public class RemoveDuplicates {

    /**
     * 用栈进行判断， 压栈的时候很栈顶的元素进行比对
     * @param s
     * @return
     */
    public String removeDuplicates(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        // 不用stack，用双向队列LinkedList
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.isEmpty() || stack.peek() != c) {
                stack.push(c);
            } else {
                stack.pop();
            }
        }
        String result = "";
        while (!stack.isEmpty()) {
            result = stack.pop() + result;
        }
        return result;

    }

}
