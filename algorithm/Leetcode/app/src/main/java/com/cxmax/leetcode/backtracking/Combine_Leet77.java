package com.cxmax.leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 回溯法也可以叫做回溯搜索法，它是一种搜索的方式。
 * 回溯法，一般可以解决如下几种问题：
 * <p>
 * 组合问题：N个数里面按一定规则找出k个数的集合
 * 切割问题：一个字符串按一定规则有几种切割方式
 * 子集问题：一个N个数的集合里有多少符合条件的子集
 * 排列问题：N个数按一定规则全排列，有几种排列方式
 * 棋盘问题：N皇后，解数独等等
 * <p>
 * <p>
 * 组合无序，排列有序
 * <p>
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * https://leetcode.cn/problems/combinations/
 * <p>
 * Created by caixi on 2022/6/21.
 */
public class Combine_Leet77 {

    private List<List<Integer>> result = new ArrayList<>();
    private LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> combine(int n, int k) {
        // 从1开始
        combineHelper(n, k, 1);
        return result;
    }

    /**
     *
     * @param n 从n个数
     * @param k 找几位数的排列组合
     * @param startIndex 起始位置
     */
    private void combineHelper(int n, int k, int startIndex) {
        // 终止条件
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < (n - (k - path.size()) + 1); i++) {
            path.add(i);
            combineHelper(n, k, i + 1);
            path.removeLast();
        }
    }

}
