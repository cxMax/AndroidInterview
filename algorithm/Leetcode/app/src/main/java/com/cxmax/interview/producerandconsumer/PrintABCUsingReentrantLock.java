package com.cxmax.interview.producerandconsumer;

import java.util.concurrent.locks.ReentrantLock;

/**
 * https://developer.aliyun.com/article/776793
 * <p>
 * 三个线程分别打印 A，B，C，要求这三个线程一起运行，打印 n 次，输出形如“ABCABCABC....”的字符串
 * <p>
 * 使用ReentrantLock来做
 * <p>
 * Created by caixi on 2022/8/6.
 */
public class PrintABCUsingReentrantLock {

    // 打印次数
    private int times = 1;
    // 当前状态
    private int state;
    // 锁
    private ReentrantLock lock = new ReentrantLock();

    /**
     * ReentrantLock 主要是使用lock 和 unlock关键字来做
     *
     * @param letter
     * @param target
     */
    private void print(String letter, int target) {
        for (int i = 0; i < times; i++) {
            lock.lock();
            if (state % 3 == target) {
                state++;
                System.out.print(letter);
            }
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        PrintABCUsingReentrantLock lock = new PrintABCUsingReentrantLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.print("A", 0);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.print("B", 1);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.print("C", 2);
            }
        }).start();
    }
}
