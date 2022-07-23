package com.cxmax.third.stack;

import java.util.Stack;

/**
 * 232. 用栈实现队列
 * <p>
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 * <p>
 * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）
 * <p>
 * Created by caixi on 2022/7/23.
 */
public class MyQueue {

    private Stack<Integer> mInStack;
    private Stack<Integer> mOutStack;

    /**
     * 用两个栈来实现队列，先进先出；
     *
     * 1. 进一个栈，出一个栈
     *
     */
    public MyQueue() {
        mInStack = new Stack<>();
        mOutStack = new Stack<>();
    }

    public void push(int x) {
        mInStack.push(x);
    }

    public int pop() {
        adjustOutStack();
        return mOutStack.pop();
    }

    public int peek() {
        adjustOutStack();
        return mOutStack.peek();
    }

    public boolean empty() {
        return mInStack.isEmpty() && mOutStack.isEmpty();
    }

    private void adjustOutStack() {
        if (!mOutStack.isEmpty()) {
            return;
        }
        while(!mInStack.empty()) {
            mOutStack.push(mInStack.pop());
        }
    }


}
