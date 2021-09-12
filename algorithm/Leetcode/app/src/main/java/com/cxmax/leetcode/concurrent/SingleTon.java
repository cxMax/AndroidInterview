package com.cxmax.leetcode.concurrent;

/**
 * @describe :
 * @usage :
 * <p>
 *     简单考察单例模式的写法，
 *     说真的， 让你这个就很侮辱人了。
 *     不过自己没做好的地方， 查漏补缺把它做好呗
 * </p>
 * Created by caixi on 9/12/21.
 */
public class SingleTon {

    public SingleTon() {
    }

    private static volatile SingleTon sInstance;

    /**
     * 懒汉式 - 用到才加载
     * @return
     */
    public static synchronized SingleTon getInstance() {
        if (sInstance == null) {
            sInstance = new SingleTon();
        }
        return sInstance;
    }

    /**
     * double check
     * 懒汉式写法 - 为什么要用volatile修饰， 取消重排序
     * @return
     */
    public static SingleTon getsInstance1() {
        if (sInstance == null) {
            synchronized (SingleTon.class) {
                if (sInstance == null) {
                    sInstance = new SingleTon();
                }
            }
        }
        return sInstance;
    }

    /**
     * 饿汉式 - 初始化的时候就加载， 但要注意final语义
     * 加载类的时候， 即会被初始化
     */
    public static final SingleTon SINSTANCE = new SingleTon();

    public static SingleTon getInstance2() {
        return  SINSTANCE;
    }

    private static class SingleHolder {
        private static final SingleTon INSTANCE = new SingleTon();
    }

    /**
     * 懒汉式
     * @return
     */
    public static SingleTon getInstance3() {
        return SingleHolder.INSTANCE;
    }

    /**
     * 枚举写法， 本身就线程安全
     */
    public enum EasySingleton{
        INSTANCE;
    }
    public static EasySingleton getInstance4() {
        return EasySingleton.INSTANCE;
    }


}
