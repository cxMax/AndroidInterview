package com.android.test1.sort;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 8/8/21.
 */
public class CountSort {

    /*
     * 记数排序 :  计数排序需要占用大量空间，它仅适用于数据比较集中的情况。比如 [0~100]，[10000~19999] 这样的数据
     *
     * 需要三个数组
     *
     * reference : https://www.cnblogs.com/zer0Black/p/6169858.html
     */
    public static void countSort(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        //找出数组中的最大最小值
        for (int i = 0; i < array.length; i++) {
            max = Math.max(max, array[i]);
            min = Math.min(min, array[i]);
        }

        int[] help = new int[max];

        //找出每个数字出现的次数
        for (int i = 0; i < array.length; i++) {
            int mapPos = array[i] - min;
            help[mapPos]++;
        }

        int index = 0;
        for (int i = 0; i < help.length; i++) {
            while (help[i]-- > 0) {
                array[index++] = i + min;
            }
        }

    }

}
