package com.cxmax.third.hash;

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
 * Created by caixi on 2022/7/26.
 */
public class CommonChars {

    /**
     * 小写字符， 统计频率，都是hash法
     *
     * 1. 先遍历第一个字符串，更新频率到数组中
     * 2. 在遍历其他的字符串，更新出现最小频率到数组中
     * 3. 最后遍历取结果
     *
     * @param words
     * @return
     */
    public List<String> commonChars(String[] words) {
        // 异常判断
        if (words == null || words.length == 0) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        int[] ret = new int[26];
        // 先第一次遍历
        for (int i = 0; i < words[0].length(); i++) {
            ret[words[0].charAt(i) - 'a'] += 1;
        }
        for (int i = 1; i < words.length; i++) {
            // 统计其他字符串出现频率
            int[] other = new int[26];
            for (int j = 0; j < words[i].length(); j++) {
                other[words[i].charAt(j) - 'a'] += 1;
            }
            // 更新结果数组
            for (int j = 0; j < ret.length; j++) {
                // todo caixi 2022-7-26 第二个做错了，是这个下标要用j
                ret[j] = Math.min(ret[j], other[j]);
            }
        }
        for (int i = 0; i < ret.length; i++) {
            // 重复数字
            while (ret[i] != 0) {
                // todo caixi 2022-7-26 第一个做错了，是这个地方， 还原的时候，直接加上i
                char c = (char) ('a' + i);
                result.add(String.valueOf(c));
                ret[i]--;
            }
        }
        return result;
    }
}
