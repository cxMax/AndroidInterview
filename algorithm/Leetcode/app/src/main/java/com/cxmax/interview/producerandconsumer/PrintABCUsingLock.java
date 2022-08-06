package com.cxmax.interview.producerandconsumer;

/**
 * https://developer.aliyun.com/article/776793
 * <p>
 * 三个线程分别打印 A，B，C，要求这三个线程一起运行，打印 n 次，输出形如“ABCABCABC....”的字符串
 * <p>
 * 使用synchronized来做， 主要是用wait和notify
 * Created by caixi on 2022/8/6.
 */
public class PrintABCUsingLock {
    private int times = 1;
    private int state = 0;

    private final Object lock = new Object();


    /**
     *
     * synchronized 主要配合 object来做
     * 主要是notify 和 wait来做
     *
     * @param letter
     * @param target
     */
    private void print(String letter, int target) {
        synchronized (lock) {
            for (int i = 0; i < times; i++) {
                if (state % 3 != target) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                state++;
                System.out.print(letter);
                lock.notify();
            }
        }

    }

    public static void main(String[] args) {
        PrintABCUsingLock p = new PrintABCUsingLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                p.print("A", 0);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                p.print("B", 0);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                p.print("C", 0);
            }
        }).start();
    }

}
