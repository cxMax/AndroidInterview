package com.cxmax.interview.producerandconsumer;

import java.util.concurrent.locks.LockSupport;

/**
 * https://developer.aliyun.com/article/776793
 * <p>
 * 三个线程分别打印 A，B，C，要求这三个线程一起运行，打印 n 次，输出形如“ABCABCABC....”的字符串
 * <p>
 * 使用LockSupport来做
 *
 * Created by caixi on 2022/8/6.
 */
public class PrintABCUsingLockSupport {
    private static Thread a, b, c;

    /**
     *
     * 使用LockSupport直接操作线程，无需配合thread使用
     *
     * @param args
     */
    public static void main(String[] args) {
        a = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    // 打印当前线程名称
                    System.out.print("A");
                    // 唤醒下一个线程
                    LockSupport.unpark(b);
                    // 当前线程阻塞
                    LockSupport.park();
                }
            }
        });
        b = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    // 打印当前线程名称
                    System.out.print("B");
                    // 唤醒下一个线程
                    LockSupport.unpark(c);
                    // 当前线程阻塞
                    LockSupport.park();
                }
            }
        });
        c = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    // 打印当前线程名称
                    System.out.print("C");
                    // 唤醒下一个线程
                    LockSupport.unpark(a);
                    // 当前线程阻塞
                    LockSupport.park();
                }
            }
        });
        a.start();
        b.start();
        c.start();

    }
}
