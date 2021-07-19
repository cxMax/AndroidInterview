package com.android.test1.string;

/**
 * @describe :
 * @usage :
 * <p>
 *     541.反转字符串 II
 * </p>
 * Created by caixi on 7/19/21.
 */
public class ReverseStr2_Leet541 {

    /**
     * 还是双指针来操作
     * @param s
     * @param k
     * @return
     */
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i += (2 * k)) {
            int left = i;
            // 这里要考虑，end超出s.length()的时候，这个是
            int right = Math.min(s.length() - 1, i + k - 1);
            while(right > left) {
               char tmp =  chars[left];
               chars[left] = chars[right];
               chars[right] = tmp;
               left++;
               right--;
            }
        }
        return new String(chars);

    }
}
