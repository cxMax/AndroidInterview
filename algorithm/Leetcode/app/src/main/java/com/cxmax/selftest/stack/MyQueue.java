package com.cxmax.selftest.stack;

import java.util.Stack;

/**
 * 232. 用栈实现队列
 * <p>
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 * <p>
 * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）
 * <p>
 * Created by caixi on 2022/3/2.
 */
public class MyQueue {
    // 用两个栈stack， 来实现先入先出队列
    Stack<Integer> stackIn;
    Stack<Integer> stackOut;

    public MyQueue() {
        stackIn = new Stack<>();
        stackOut = new Stack<>();
    }

    /**
     * 往 stackIn push
     * 1， 2， 3
     * @param x
     */
    public void push(int x) {
        stackIn.push(x);

    }

    public int pop() {
        dumpstackIn();
        return stackOut.pop();
    }

    public int peek() {
        dumpstackIn();
        return stackOut.peek();
    }

    public boolean empty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }

    /**
     * 只要是遇到peek、pop等操作，都需要调用此方法
     * 如果stackOut为空了
     * 就把stackIn的所有元素放进去
     */
    private void dumpstackIn(){
        if (!stackOut.isEmpty()) {
            return;
        }
        while (!stackIn.isEmpty()) {
            stackOut.push(stackIn.pop());
        }
    }
}
