package com.android.test1.sort;

/**
 * @describe :
 * @usage :
 * <p>
 *     桶排序
 *     算法时间复杂度O(n)
 *     http://www.cnblogs.com/skywang12345/p/3602737.html
 * </p>
 * Created by caixi on 8/8/21.
 */
public class BucketSort {

    public static void bucketSort(int[] arr, int max) {
        int[] buckets;

        if (arr == null || arr.length == 0 || max < 1)
            return;

        // 创建一个容量为max的数组buckets，并且将buckets中的所有数据都初始化为0。
        buckets = new int[max];

        // 1. 计数
        for (int i = 0; i < arr.length; i++)
            buckets[arr[i]]++;

        // 2. 排序
        for (int i = 0, j = 0; i < max; i++) {
            while ((buckets[i]--) > 0) {
                arr[j++] = i;
            }
        }

        buckets = null;

    }


}
