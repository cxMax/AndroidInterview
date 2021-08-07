package com.android.test1.stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @describe :
 * @usage :
 * <p>
 * 20. 有效的括号
 *
 * 判断是否匹配，用stack来做
 * </p>
 * Created by caixi on 8/7/21.
 */
public class IsValid_Leet20 {

    public boolean isValid(String s) {
        Deque<Character> queue = new LinkedList<>();
        if (s == null || s.length() == 0) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                queue.push(')');
            } else if (ch == '[') {
                queue.push(']');
            } else if (ch == '{') {
                queue.push('}');
            } else if (queue.isEmpty() || queue.peek() != ch) {
                return false;
            } else {
                queue.pop();
            }
        }
        return queue.isEmpty();
    }

}
