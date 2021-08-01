package com.android.test1.string;

/**
 * @describe :
 * @usage :
 * <p>
 * 剑指 Offer 05. 替换空格
 * </p>
 * Created by caixi on 7/19/21.
 */
public class ReplaceSpace_Offer05 {


    public String replaceSpace(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(0);
            if (c == ' ') {
                sb.append("%20");
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }


}
