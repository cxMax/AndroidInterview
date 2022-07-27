package com.cxmax.third.string;

/**
 * 344. 反转字符串
 * <p>
 * https://leetcode-cn.com/problems/reverse-string/
 * <p>
 * Created by caixi on 2022/7/27.
 */
public class ReverseString {


    /**
     * 交换字符串， 双指针大法
     *
     * 这道理，我理解有点像冒泡排序，这次做，也是大概看了下，没看细节，蒙着眼睛做的
     *
     * @param s
     */
    public void reverseString(char[] s) {
        // 异常判断
        if (s == null || s.length == 0) {
            return;
        }
        int left = 0;
        int right = s.length - 1;
        // 这里注意判断条件
        while(left < right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
            left++;
            right--;
        }
    }

}
