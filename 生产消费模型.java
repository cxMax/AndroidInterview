package com.huajiao.yuewan.gift;

import android.util.Log;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 2020-02-19.
 */
public class ProducerAndConsumer {

    /**
     * 使用wait() \ notify()来实现
     */
    public static class Stock1 {

        private String name;
        // 标记库存是否有内容
        private boolean hasComputer = false;

        public synchronized void putOne(String name) {
            while(hasComputer){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.name = name;
            System.out.println("生产者...生产了 " + name);
            this.hasComputer = true;
            this.notifyAll();
        }


        public synchronized void takeOne() {
            while (!hasComputer) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("消费者...消费了 " + name);
            this.hasComputer = false;
            this.notifyAll();
        }


        public static void main(String[] args) {
            final Stock1 computer = new Stock1();
            Thread p1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        computer.putOne("Dell");
                    }

                }
            });
            Thread p2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        computer.putOne("Mac");
                    }
                }
            });
            Thread c1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        computer.takeOne();
                    }

                }
            });
            Thread c2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        computer.takeOne();
                    }
                }
            });
            p1.start();
            p2.start();
            c1.start();
            c2.start();
        }
    }


    public static class LockStock {
        final Lock lock = new ReentrantLock();
        final Condition notFull = lock.newCondition();
        final Condition notEmpty = lock.newCondition();
        private static Integer count = 0;
        private static final Integer FULL = 10;

        class Producer implements Runnable {

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(3000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //获取锁
                    lock.lock();
                    try {
                        while (count == FULL) {
                            try {
                                notFull.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        count++;
                        Log.e("info", Thread.currentThread().getName()
                                + "生产者生产，目前总共有" + count);
                        //唤醒消费者
                        notEmpty.signal();
                    } finally {
                        //释放锁
                        lock.unlock();
                    }
                }

            }
        }

        class Consumer implements Runnable {

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    lock.lock();
                    try {
                        while (count == 0) {
                            try {
                                notEmpty.await();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        count--;
                        Log.e("info", Thread.currentThread().getName()
                                + "消费者消费，目前总共有" + count);
                        notFull.signal();
                    } finally {
                        lock.unlock();
                    }
                }

            }
        }

        public  void main() {
            new Thread(new Producer()).start();
            new Thread(new Consumer()).start();
            new Thread(new Producer()).start();
            new Thread(new Consumer()).start();
            new Thread(new Producer()).start();
            new Thread(new Consumer()).start();
        }
    }



}
