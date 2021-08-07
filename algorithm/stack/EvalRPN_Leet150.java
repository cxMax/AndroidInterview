package com.android.test1.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 8/7/21.
 */
public class EvalRPN_Leet150 {

    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<>();
        for (String token : tokens) {
            char ch = token.charAt(0);
            if (!isOpe(token)) {
                stack.push(stoi(token));
            } else if (ch == '+') {
                stack.push(stack.pop() + stack.pop());
            } else if (ch == '-') {
                stack.push(-stack.pop() + stack.pop());
            } else if (ch == '*') {
                stack.push(stack.pop() * stack.pop());
            } else {
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(num2 / num1);
            }
        }
        return stack.pop();
    }

    /**
     * 判断是不是操作符
     * @param s
     * @return
     */
    private boolean isOpe(String s) {
        return s.length() == 1 && s.charAt(0) <'0' || s.charAt(0) >'9';
    }
    private int stoi(String s) {
        return Integer.valueOf(s);
    }


}
