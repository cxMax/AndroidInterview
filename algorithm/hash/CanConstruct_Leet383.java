package com.android.test1.hash;

/**
 * @describe :
 * @usage :
 * <p>
 * 判断第一个字符串 ransom 能不能由第二个字符串 magazines 里面的字符构成。如果可以构成，返回 true ；否则返回 false。
 * 输入：ransomNote = "aa", magazine = "aab"
 * 输出：true
 * </p>
 * Created by caixi on 7/21/21.
 */
public class CanConstruct_Leet383 {


    public boolean canConstruct(String ransomNote, String magazine) {
        // 因为26个字母嘛， 小写
        int[] array = new int[26];
        // 第一个字符串,
        for (int i = 0; i < magazine.length(); i++) {
            array[magazine.charAt(i) - 'a'] += 1;
        }

        // 第二个字符串
        for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            if (array[c - 'a'] > 0) {
                array[c - 'a']--;
            } else {
                return false;
            }
        }
        return true;
    }

}
