package com.cxmax.third.hash;

import java.util.HashSet;

/**
 * 202. 快乐数
 *
 * https://leetcode-cn.com/problems/happy-number/
 *
 * 解释：
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 *
 * Created by caixi on 2022/7/26.
 */
public class IsHappy {


    /**
     * 这道题，先明白什么是快乐数?
     *
     * 1. 19 = 个位数的平方 + 十位数的平凡， 一次递归下去，直到等于1了
     *
     * 所以这道题，怎么解?
     *
     * 1. 使用HashSet，跳出循环结果就是 ！= 1，并且一直循环下去都是1，也就是结果HashSet有1了
     * 2. 怎么取计算下一个数字，固定算法，先取余，在做加法，在缩小10
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        while(n != 1 && !set.contains(n)) {
            // todo caixi 2022-7-26 第一个地方做错了， 是这里，要先添加n到集合
            set.add(n);
            n = getNextNum(n);
        }
        // todo caixi 2022-7-27 第二个地方做错了，是这里，结果判断n是否等于1
        return n == 1;
    }

    private int getNextNum(int n) {
        int ret = 0;
        while(n > 0) {
            int num = n % 10;
            ret += num * num;
            n = n /10;
        }
        return ret;
    }
}
