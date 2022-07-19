package com.cxmax.selftest.hash;

import java.util.HashMap;
import java.util.HashSet;

/**
 *
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
 * Created by caixi on 2022/1/27.
 */
public class IsHappy {

    /**
     * 使用hashmap来处理这个问题
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        HashSet<Integer> result = new HashSet<>();
        // 为什么不等于1， 主要是结果，快乐数嘛， 必须要1终止条件
        while(n != 1 && !result.contains(n)) {
            result.add(n);
            n = getNextNum(n);
        }
        return n == 1;
    }

    /**
     * 固定算法
     *
     * 1. 先取余数，
     * 2. 在进行平方
     * 3. 再取除法
     * 4. 叠加 除法后但平方的值
     *
     * @param n
     * @return
     */
    private int getNextNum(int n) {
        int res = 0;
        while (n > 0) {
            int temp = n % 10;
            res += temp * temp;
            n = n / 10;
        }
        return res;
    }

}
