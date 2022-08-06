package com.cxmax.interview.producerandconsumer;

import java.util.concurrent.Semaphore;

/**
 * https://developer.aliyun.com/article/776793
 * <p>
 * 三个线程分别打印 A，B，C，要求这三个线程一起运行，打印 n 次，输出形如“ABCABCABC....”的字符串
 * <p>
 * <p>
 * 使用 Semaphore 来做
 * <p>
 * Created by caixi on 2022/8/6.
 */
public class PrintABCUsingSemaphore {
    private int times = 1;
    private static Semaphore semaphoreA = new Semaphore(1); // 只有A 初始信号量为1,第一次获取到的只能是A
    private static Semaphore semaphoreB = new Semaphore(0);
    private static Semaphore semaphoreC = new Semaphore(0);

    public static void main(String[] args) {
        PrintABCUsingSemaphore printer = new PrintABCUsingSemaphore();
        new Thread(() -> {
            printer.print("A", semaphoreA, semaphoreB);
        }, "A").start();

        new Thread(() -> {
            printer.print("B", semaphoreB, semaphoreC);
        }, "B").start();

        new Thread(() -> {
            printer.print("C", semaphoreC, semaphoreA);
        }, "C").start();
    }

    /**
     * semaphoreC 感觉和condition使用类似，不同的是，他不需要lock辅助
     *
     * @param name
     * @param current
     * @param next
     */
    private void print(String name, Semaphore current, Semaphore next) {
        for (int i = 0; i < times; i++) {
            try {
                System.out.println("111" + Thread.currentThread().getName());
                current.acquire();  // A获取信号执行,A信号量减1,当A为0时将无法继续获得该信号量
                System.out.print(name);
                next.release();    // B释放信号，B信号量加1（初始为0），此时可以获取B信号量
                System.out.println("222" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
