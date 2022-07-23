package com.cxmax.third.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 225. 用队列实现栈
 * <p>
 * https://leetcode-cn.com/problems/implement-stack-using-queues/
 * <p>
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈
 * <p>
 * Created by caixi on 2022/7/23.
 */
public class MyStack {

    private Queue<Integer> queue1 = new LinkedList<>();
    private Queue<Integer> queue2 = new LinkedList<>();


    /**
     *  这道题，
     *  1. queue1 和 stack内部的元素顺序，是相反的。
     *  1.
     */
    public MyStack() {

    }

    public void push(int x) {
        queue2.offer(x);
        while(!queue1.isEmpty()) {
            queue2.offer(queue1.poll());
        }
        // 这个时候queue2，的顺序就是3，2，1，再把他交换到queue1
        Queue<Integer> tmp;
        tmp = queue2;
        // 这里基本上就是null了
        queue2 = queue1;
        queue1 = tmp;
    }

    public int pop() {
       return queue1.poll();
    }

    public int top() {
        return queue1.peek();
    }

    public boolean empty() {
        return queue1.isEmpty();
    }
}
