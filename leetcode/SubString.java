package com.art.editor.editor;

import android.text.TextUtils;
import android.util.Log;

/**
 * @describe : 截取字符串，字符串中有汉字和英文，截取出来的不能包含半个汉字
 * @usage :
 * <p>
 *      Java中, 在内存中,字母和符号占一个字节,而汉字占两个或三个字节
 * </p>
 * Created by caixi on 12/23/20.
 */
public class SubString {

    public static void main(String[] args) {
        String content = "hcsm妈妈说妈妈什么吗smoap";
        int count = 6;
        Log.e("info", "main: " + subString(content, count));
    }

    public static String subString(String content, int length) {
        if (TextUtils.isEmpty(content) || content.length() < length || length == 0) {
            return "";
        }
        char[] c = content.toCharArray();
        int len = 0;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            String cStr = String.valueOf(c[i]);
            len += cStr.getBytes().length;
            if(len > length) {
                break;
            }
            result.append(cStr);
        }
        return result.toString();
    }



}
