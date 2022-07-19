package com.cxmax.selftest.stack;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 347.前 K 个高频元素
 * <p>
 * https://leetcode-cn.com/problems/top-k-frequent-elements/
 * <p>
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * <p>
 * 输入: nums = [1,1,1,2,2,3], k = 2  输出: [1,2]
 * <p>
 * Created by caixi on 2022/3/7.
 */
public class TopKFrequent {

    /**
     * 1. 出现频率，第一时间想到HashMap
     * <p>
     * 2. 排序，想到两个，快排， 和优先级队列
     *
     * @param nums
     * @param k
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public int[] topKFrequent(int[] nums, int k) {
        if(nums.length == 0) {
            return new int[0];
        }
        Map<Integer, Integer> map = new HashMap<>();
        // 统计频率
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        // 排序,  使用优先级队列来做
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });

        for (Map.Entry<Integer, Integer> en : map.entrySet()) {
            queue.offer(en);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = queue.poll().getValue();
        }
        return result;

    }

}
