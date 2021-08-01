package com.android.test1;

/**
 * @describe :
 * @usage :
 * <p>
 * 实现 int sqrt(int x) 函数。
 * <p>
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * <p>
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4
 * 输出: 2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sqrtx
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </p>
 * Created by caixi on 6/25/21.
 */
public class MySqrt_Leet69 {

    /**
     * 二分查找来做， 这里有个问题， 就是注意int型的范围， 算平方的话， 会取int型的最大值， 所以有个强制类型转换
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        int left = 0;
        int right = x;
        while(left <= right) {
            int middle = left + ((right - left) >> 1);
            if (((long)middle * middle) > x) {
                right = middle - 1;
            } else if (((long)middle * middle)  < x) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return right;
    }

}
