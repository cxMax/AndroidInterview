package com.cxmax.selftest.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 * <p>
 * 150. 逆波兰表达式求值
 * <p>
 * 有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 * <p>
 * 输入：tokens = ["4","13","5","/","+"]
 * <p>
 * 输出：6
 * <p>
 * Created by caixi on 2022/3/4.
 */
public class EvalRPN {

    /**
     * 和 1047. 删除字符串中的所有相邻重复项 一样
     * <p>
     * 也是使用栈的特性来做
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        // 同样使用双端队列， 来做栈
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < tokens.length; i++) {
            String s = tokens[i];
            if ("+".equals(s)) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(num1 + num2);
            } else if ("-".equals(s)) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(num2 - num1);
            } else if ("*".equals(s)) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(num1 * num2);
            } else if ("/".equals(s)) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(num2 / num1);
            } else {
                stack.push(Integer.parseInt(s));
            }
        }
        return stack.pop();


    }

}
