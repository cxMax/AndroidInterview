package com.android.test1.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @describe :
 * @usage :
 * <p>
 *     删除相邻字符串的重复项
 * </p>
 * Created by caixi on 8/7/21.
 */
public class RemoveDuplicates_Leet1047 {

    public String removeDuplicates(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (stack.isEmpty() || stack.peek() != ch) {
                stack.push(ch);
            } else {
                stack.pop();
            }
        }
        String result = "";
        while(!stack.isEmpty()) {
            result = stack.pop() + result;
        }
        return result;

    }
}
