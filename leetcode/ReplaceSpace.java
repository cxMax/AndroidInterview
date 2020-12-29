package com.trailblazer.easyshare;

import android.util.Log;

/**
 * @describe :
 * @usage :
 * <p>
 *     替换字符串中的空格
 * </p>
 * Created by caixi on 12/25/20.
 */
public class ReplaceSpace {

    public static void main(String[] args) {
        String s = "We are happy.";
        Log.e("info", "main: " + replaceSpace(s));
    }

    public static String replaceSpace(String s) {
        StringBuilder sp = new StringBuilder();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                sp.append("%20");
                continue;
            }
            sp.append(chars[i]);
        }
        return sp.toString();
    }
}
