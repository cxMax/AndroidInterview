package com.android.test1.sort;

/**
 * @describe :
 * @usage :
 * <p>
 *     冒泡排序，最常见的排序算法
 *     冒泡排序的时间复杂度是O(N2)。
 * </p>
 * Created by caixi on 8/8/21.
 */
public class BubbleSort {

    /**
     * 将最大的数， 放在末尾
     * @param array
     */
    public static void bubbleSort(int[] array) {
        int i, j;
        for (i = array.length - 1; i > 0 ; i--) {
            for (j = 0;  j < i; j++) {
                // 前后比较，交换顺序
               if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
               }
            }
        }
    }

}
