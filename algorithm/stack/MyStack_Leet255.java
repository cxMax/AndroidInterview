package com.android.test1.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @describe :
 * @usage :
 * <p>
 * 225. 用队列实现栈
 * 队列的函数方法要注意：
 * poll - 移除并返回头部
 * offer - add添加不报错
 * </p>
 * Created by caixi on 8/7/21.
 */
public class MyStack_Leet255 {

    class MyStack {

        Queue<Integer> stack;
        Queue<Integer> help;

        /**
         * Initialize your data structure here.
         */
        public MyStack() {
            stack = new LinkedList<>();
            help = new LinkedList<>();

        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            help.offer(x);
            while (!stack.isEmpty()) {
                help.offer(stack.poll());
            }
            Queue<Integer> tmp = stack; // 这个是null的
            stack = help;
            help = tmp;
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            return stack.poll();
        }

        /**
         * Get the top element.
         */
        public int top() {
            return stack.peek();

        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return stack.isEmpty();
        }
    }

}
