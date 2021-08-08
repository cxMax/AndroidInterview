package com.android.test1.sort;

/**
 * @describe :
 * @usage :
 * <p>
 *     选择排序 : $O(n^2)$
 *     reference : http://www.cnblogs.com/shen-hua/p/5424059.html
 * </p>
 * Created by caixi on 8/8/21.
 */
public class SelectSort {

    public static void selectSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        for (int i = 0; i < array.length - 1; i++) {
            int k = i;
            for (int j = k + 1; j < array.length; j++) {
                if (array[j] < array[k]) {
                    k = j;
                }
            }
            if (i != k) {
                int temp = array[i];
                array[i] = array[k];
                array[k] = temp;
            }
        }
    }


}
