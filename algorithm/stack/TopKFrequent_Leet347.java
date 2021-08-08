package com.android.test1.stack;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @describe :
 * @usage :
 * <p>
 * 347. 前 K 个高频元素
 * </p>
 * Created by caixi on 8/8/21.
 */
public class TopKFrequent_Leet347 {


    public int[] topKFrequent(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[0];
        }
        // 返回前k个出现频次最高的数
        int[] ret = new int[k];
        // 一次遍历， 就把所有数字的频次统计出来了
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i] + 1, 0));
        }
        // 排序， 拿出前k个数字
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        for (Map.Entry en : map.entrySet()) {
            queue.add(en);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        for (int i = 0; i < k; i++) {
            ret[i] = queue.poll().getKey();
        }
        return ret;

    }


}
