package com.cxmax.interview.producerandconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * https://developer.aliyun.com/article/776793
 * <p>
 * 三个线程分别打印 A，B，C，要求这三个线程一起运行，打印 n 次，输出形如“ABCABCABC....”的字符串
 * <p>
 * 使用condition来做
 *
 * Created by caixi on 2022/8/6.
 */
public class PrintABCUsingCondition {
    // 打印次数
    private int times = 10;
    // 当前状态
    private int state = 0;

    private Lock lock = new ReentrantLock();

    private Condition a1 = lock.newCondition();
    private Condition a2 = lock.newCondition();
    private Condition a3 = lock.newCondition();

    /**
     * condition 要配合 ReetrantLock来做
     * 主要是wait和signal方法
     *
     * @param letter
     * @param target
     * @param cur
     * @param next
     */
    private void print(String letter, int target, Condition cur, Condition next) {
        for (int i = 0; i < times; i++) {
            lock.lock();
            try {
                while(state % 3 != target) {
                    cur.await();
                }
                state++;
                System.out.print(letter);
                next.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }


    }

    public static void main(String[] args) {
        PrintABCUsingCondition p = new PrintABCUsingCondition();
        new Thread(new Runnable() {
            @Override
            public void run() {
                p.print("A", 0, p.a1, p.a2);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                p.print("B", 1, p.a2, p.a3);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                p.print("C", 2, p.a3, p.a1);
            }
        }).start();
    }
}
