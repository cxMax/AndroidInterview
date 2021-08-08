package com.android.test1.sort;

/**
 * @describe :
 * @usage :
 * <p>
 *     插入排序 : 平均O(n^2),最好O(n),最坏O(n^2);空间复杂度O(1);稳定;简单
 *     reference : https://www.cnblogs.com/zengzhihua/p/4456730.html
 * </p>
 * Created by caixi on 8/8/21.
 */
public class InsertSort {

    public static void insertSort(int[] array) {
        int tmp;
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    tmp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = tmp;
                }

            }
        }

    }
}
