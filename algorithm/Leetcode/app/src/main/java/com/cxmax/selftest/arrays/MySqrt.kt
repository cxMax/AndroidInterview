package com.cxmax.selftest.arrays

/**
 * https://leetcode-cn.com/problems/sqrtx/
 *
 * 69.Sqrt(x) 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。 例如 4， 返回 2
 *
 * Created by caixi on 2021/12/30.
 */
object MySqrt {

    /**
     * 不断的二分查找，取平方，
     * @param x Int
     * @return Int
     */
    fun mySqrt(x: Int): Int {
        var left = 0
        var right = x
        var ret = -1
        while(left <= right) {
            var middle = left + ((right - left) / 2)
            if (middle.toLong() * middle <= x) {
                ret = middle
                left = middle + 1
            } else {
                right = middle - 1
            }
        }
        return ret
    }

}