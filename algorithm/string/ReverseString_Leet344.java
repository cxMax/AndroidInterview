package com.android.test1.string;

/**
 * @describe :
 * @usage :
 * <p>
 * 344. 反转字符串
 * </p>
 * Created by caixi on 7/19/21.
 */
public class ReverseString_Leet344 {

    /**
     * 双指针，反转字符串。
     * 首尾交换、并且指针向中间移动
     * @param s
     */
    public void reverseString(char[] s) {
        if (s == null || s.length == 0) {
            return;
        }
        int left = 0;
        int right = s.length - 1;
        while(right > left) {
            // 交换顺序
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
            left--;
            right++;
        }
    }

}
