package com.trailblazer.easyshare.ui.activities;

import android.util.Log;

/**
 * @describe : 是否是回文数字
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 12/29/20.
 */
public class IsPalindrome {

    public static void main(String[] args) {
        int a = 121;
        Log.e("info", "main: " + isPalindrome(a) );
    }

    public static boolean isPalindrome(int a) {
        if (a < 0) {
            return false;
        }
        int origin = a;
        int reverse = 0;
        while(a != 0) {
            int tmp = a % 10;
            reverse = reverse * 10 + tmp;
            a /= 10;
        }
        Log.e("info", "isPalindrome: " + reverse);
        return reverse == origin;
    }
}
