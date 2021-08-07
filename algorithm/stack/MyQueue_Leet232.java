package com.android.test1.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * @describe :
 * @usage :
 * <p>
 * 用栈实现队列
 * </p>
 * Created by caixi on 8/7/21.
 */
public class MyQueue_Leet232 {


    class MyQueue {
        Deque<Integer> stIn;
        Deque<Integer> stOut;

        /** Initialize your data structure here. */
        public MyQueue() {
            stIn = new ArrayDeque<>();
            stOut = new ArrayDeque<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            stIn.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if (stOut.isEmpty()) {
                while(!stIn.isEmpty()) {
                    stOut.push(stIn.pop());
                }
            }
            return stOut.pop();
        }

        /** Get the front element. */
        public int peek() {
            int value = pop();
            stOut.push(value);
            return value;

        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stIn.isEmpty() && stOut.isEmpty();
        }
    }
}
