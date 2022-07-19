package com.cxmax.selftest.hash;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 1002. 查找共用字符
 *
 * https://leetcode-cn.com/problems/find-common-characters/
 *
 * 输入：words = ["cool","lock","cook"]
 *
 * 输出：["c","o"]
 *
 * Created by caixi on 2022/1/26.
 */
public class CommonChars {

    /**
     * 这题还是用hash表来做， 用数组实现的hash表进行统计和还原
     * @param words
     * @return
     */
    public List<String> commonChars(String[] words) {
        List<String> result = new ArrayList<>();
        // 异常判断
        if (words.length == 0) {
            return result;
        }
        // 开始取第一位标准，取出字母的分布
        int[] hash = new int[26];
        for (int i = 0; i < words[0].length(); i++) {
            hash[words[0].charAt(i) - 'a'] += 1;
        }
        // 开始遍历数组其他字符串，
        for (int i = 1; i < words.length; i++) {
            int[] otherHash = new int[26];
            for (int j = 0; j < words[i].length(); j++) {
                otherHash[words[i].charAt(j) - 'a'] += 1;
            }
            // 开始比较，更新公共的字母
            for (int k = 0; k < 26; k++) {
                hash[k] = Math.min(hash[k], otherHash[k]);
            }
        }
        // 最后根据结果，还原公共字符串
        for (int i = 0; i < 26; i++) {
            while(hash[i] != 0) {
                char c= (char) (i+'a');
                result.add(String.valueOf(c));
                hash[i]--;
            }
        }
        return result;

    }

}
