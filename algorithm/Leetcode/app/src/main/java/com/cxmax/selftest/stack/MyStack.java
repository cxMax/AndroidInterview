package com.cxmax.selftest.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 225. 用队列实现栈
 * <p>
 * https://leetcode-cn.com/problems/implement-stack-using-queues/
 * <p>
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈
 * <p>
 * Created by caixi on 2022/3/3.
 */
public class MyStack {

    // 双端队列来做
    Deque<Integer> deque = new ArrayDeque<>();

    /**
     * Queue是FIFO的单向队列，Deque是双向队列。
     * <p>
     * http://www.codebaoku.com/it-java/it-java-220136.html
     */
    public MyStack() {

    }

    public void push(int x) {
        deque.addLast(x);
    }

    public int pop() {
        int size = deque.size();
        size--;
        // 这里的意思是，如果队列里面有2个以上的元素
        while (size-- > 0) {
            deque.addLast(deque.peekFirst());
            deque.pollFirst();
        }
        int res = deque.pollFirst();
        return res;

    }

    public int top() {
        return deque.peekLast();
    }

    public boolean empty() {
        return deque.isEmpty();
    }

}
