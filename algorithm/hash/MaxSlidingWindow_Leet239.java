package com.android.test1.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 8/8/21.
 */
public class MaxSlidingWindow_Leet239 {

    class MyQueue {

        Deque<Integer> link = new LinkedList<>();

        /**
         * 移除第一个元素
         * @param value
         */
        void poll(int value) {
            if (!link.isEmpty() && value == link.peek()) {
                link.poll();
            }
        }

        /**
         * 就是保证每次添加， 都是最大值， 返回的时候， 直接出队列就好了
         * @param value
         */
        void add(int value) {
            while (!link.isEmpty() && value > link.getLast()) {
                link.removeLast();
            }
            link.add(value);
        }

        int peek() {
            return link.peek();
        }
    }

    /**
     * 其实这题的思路， 就是构造一个队列， 队列置顶就直接返回最大值
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length <= 1) {
            return nums;
        }
        int len = nums.length - k + 1;
        int[] ret = new int[len];
        int index = 0;
        MyQueue myQueue = new MyQueue();
        // 开始初始化窗口
        for (int i = 0; i < k; i++) {
            myQueue.add(nums[i]);
        }
        ret[index++] = myQueue.peek();
        for (int i = k; i < nums.length; i++) {
            //开始滑动窗口，移除最先入队列的元素
            myQueue.poll(nums[i - k]);
            //添加元素
            myQueue.add(nums[i]);
            //获取最值
            ret[index++] = myQueue.peek();

        }
        return ret;


    }

}
