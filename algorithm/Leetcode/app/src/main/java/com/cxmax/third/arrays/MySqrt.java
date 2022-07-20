package com.cxmax.third.arrays;

/**
 * https://leetcode-cn.com/problems/sqrtx/
 * <p>
 * 69.Sqrt(x) 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。 例如 4， 返回 2
 * <p>
 * Created by caixi on 2022/7/20.
 */
public class MySqrt {


    /**
     * 求一个非负整数的算术平方根
     *
     * 这道题的算法是不断的去取算术平方根
     *
     * 左边界限就是0
     * 有边界线就是目标数字
     *
     * 不断的取中间值 算平方，最后得出平方根
     *
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        int left = 0;
        int right = x;
        int ret = 0;
        while(left <= right) {
            int mid = left + ((right - left) >> 1);
            // todo caixi 2022-7-20 注意，这里要 <= ，因为是无限趋近于x
            if (mid * mid <= x) {
                ret = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ret;
    }

}
