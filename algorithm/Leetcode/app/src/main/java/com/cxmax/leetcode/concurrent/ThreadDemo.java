package com.cxmax.leetcode.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @describe :
 * @usage :
 * <p>
 *     按顺序一次输出A、B、C
 * </p>
 * Created by caixi on 9/10/21.
 */
public class ThreadDemo {


    /**
     * 通过Thread join 来实现
     * join 线程执行完毕后再执行
     */
    static class ThreadJoin {
        static Thread a = new Thread() {
            @Override
            public void run() {
                System.out.println("A");
            }
        };
        static Thread b = new Thread() {
            @Override
            public void run() {
                try {
                    a.join();
                    System.out.println("B");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        static Thread c = new Thread() {
            @Override
            public void run() {
                try {
                    b.join();
                    System.out.println("C");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        public static void main(String[] args) {
            a.start();
            b.start();
            c.start();
        }
    }

    /**
     * 主线程join 来实现
     * 在父进程调用子进程的join()方法后，父进程需要等待子进程运行完再继续运行。
     */
    static class MainThreadJoin {
        static Thread a = new Thread() {
            @Override
            public void run() {
                System.out.println("A");
            }
        };
        static Thread b = new Thread() {
            @Override
            public void run() {
                System.out.println("B");
            }
        };
        static Thread c = new Thread() {
            @Override
            public void run() {
                System.out.println("C");
            }
        };

        public static void main(String[] args) throws InterruptedException {
            a.start();
            a.join();
            b.start();
            b.join();
            c.start();
        }
    }

    /**
     * Object wait 和 notify来做， 两个锁
     */
    static class WaitThread {
        private static final Object lock1 = new Object();
        private static final Object lock2 = new Object();

        private static boolean aRun = false;
        private static boolean bRun = false;


        static Thread a = new Thread() {
            @Override
            public void run() {
                synchronized(lock1) {
                    System.out.println("A");
                    aRun = true;
                    lock1.notify();
                }
            }
        };
        static Thread b = new Thread() {
            @Override
            public void run() {
                synchronized (lock1) {
                    try {
                        if (!aRun) {
                            lock1.wait();
                        }
                        synchronized (lock2) {
                            System.out.println("B");
                            bRun = true;
                            lock2.notify();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        static Thread c = new Thread() {
            @Override
            public void run() {
                synchronized (lock2) {
                    try {
                        if (!bRun) {
                            lock2.wait();
                        }
                        System.out.println("C");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        public static void main(String[] args) {
            a.start();
            b.start();
            c.start();
        }


    }

    /**
     * 通过condition来解决
     */
    static class ConditionThread {
        private static ReentrantLock lock = new ReentrantLock();

        private static Condition condition1 = lock.newCondition();
        private static Condition condition2 = lock.newCondition();
        private static boolean aRun = false;
        private static boolean bRun = false;

        static Thread a = new Thread() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    condition1.signal();
                } finally {
                    lock.unlock();
                }
                System.out.println("A");
            }
        };
        static Thread b = new Thread() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    if (!aRun) {
                        condition1.wait();
                    }
                    System.out.println("B");
                    condition2.signal();
                } catch (InterruptedException e){
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        };
        static Thread c = new Thread() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    if (!bRun) {
                        condition2.await();
                    }
                    System.out.println("C");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };

        public static void main(String[] args) {
            a.start();
            b.start();
            c.start();
        }
    }

    /**
     * 通过CountDownLatch来解决
     * 计时为0向下执行
     */
    static class CountDownLatchThread {

        private static CountDownLatch latch1 = new CountDownLatch(1);
        private static CountDownLatch latch2 = new CountDownLatch(1);

        static Thread a = new Thread() {
            @Override
            public void run() {
                latch1.countDown();
                System.out.println("A");
            }
        };
        static Thread b = new Thread() {
            @Override
            public void run() {
                try {
                    latch1.await();
                    System.out.println("B");
                    latch2.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        static Thread c = new Thread() {
            @Override
            public void run() {
                try {
                    latch2.await();
                    System.out.println("C");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        public static void main(String[] args) {
            a.start();
            b.start();
            c.start();
        }
    }
}
