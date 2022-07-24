package com.cxmax.third.stack;

import java.util.Stack;

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
 * Created by caixi on 2022/7/24.
 */
public class EvalRPN {

    /**
     * 我觉得，这道题主要是理解，就以下几个点
     *
     * 1. 和去除相邻重复字符一样，用堆栈来做
     * 2. 遇到数字，直接进栈
     * 3. 遇到运算符号，就直接弹出数字，再压栈
     *
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            String s = tokens[i];
            if (s.equals("+")) {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                stack.push(String.valueOf(num2 + num1));
            } else if (s.equals("-")) {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                stack.push(String.valueOf(num2 - num1));
            }  else if (s.equals("*")) {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                stack.push(String.valueOf(num2 * num1));
            }  else if (s.equals("/")) {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                stack.push(String.valueOf(num2 / num1));
            } else {
                // 数字直接进栈
                stack.push(s);
            }
        }
        // todo caixi 2022-7-24 这里很让我出乎医疗意料啊， 我感觉这里有异常判断，不过直接就写出来了
        return Integer.parseInt(stack.pop());


    }
}
