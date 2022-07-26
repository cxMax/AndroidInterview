package com.cxmax.third.hash;

/**
 *
 * 383. 赎金信
 *
 * https://leetcode.cn/problems/ransom-note/
 *
 * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 *
 * 如果可以，返回 true ；否则返回 false 。
 *
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/ransom-note
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。¬
 *
 * Created by caixi on 2022/7/26.
 */
public class CanConstruct {


    /**
     * 感觉跟查找共用字符这道题很类似啊
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote == null || ransomNote.length() == 0 || magazine == null || magazine.length() == 0) {
            return false;
        }
        // 26个字母
        int[] ret = new int[26];
        // todo caixi 2022-7-26 第一个地方，做错了。是要弄清楚，由后面这个构成前面那个单词，所以要先便利后面这个
        for (int i = 0; i < magazine.length(); i++) {
            ret[magazine.charAt(i) - 'a'] += 1;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            ret[ransomNote.charAt(i) - 'a'] -= 1;
        }
        for (int i = 0; i < ret.length; i++) {
            //  todo caixi 2022-7-26 这里只要有一个字母在前面那个字符串没有，即小于0，就是false，这里跟公共字符不一样
            if (ret[i] < 0) {
                return false;
            }
        }
        return true;
    }


}
