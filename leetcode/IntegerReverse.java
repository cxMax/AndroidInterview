package com.art.editor.editor;


import android.util.Log;

/**
 * @describe :
 * @usage :
 * <p>
 *     整数反转 ， 32位， 不能超过最大数， 还有正负数
 * </p>
 * Created by caixi on 12/23/20.
 */
public class IntegerReverse {

    public static void main(String[] args) {
        int x = -123;
        Log.e("info", "main: " + reverse(x) );

    }

    /**
     * 核心思路就是， 除以10的余数。 作为首位数字，不断乘以10
     * @param x
     * @return
     */
    public static int reverse(int x) {
        int result = 0;
        while(x != 0) {
            int tmp = x % 10;
            // 这里主要判断，最后结果的时候，是否越界超出
            if (result > Integer.MAX_VALUE || (result == Integer.MAX_VALUE && tmp == 8)) {
                return 0;
            }
            if (result < Integer.MIN_VALUE || (result == Integer.MIN_VALUE && tmp == 7)) {
                return 0;
            }
            result = result * 10 + tmp;
            x /= 10;
        }
        return result;
    }
}
