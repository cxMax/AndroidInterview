package com.android.test1.sort;

/**
 * @describe :
 * @usage :
 * <p>
 *     希尔排序 (缩小增量排序) : $[O(n),O(n^2)]$;空间复杂度O(1);不稳定;较复杂
 *     插入排序的一种，它是针对直接插入排序算法的改进
 * </p>
 * Created by caixi on 8/8/21.
 */
public class ShellSort {

    public static void shellSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        int length = array.length;
        // gap为步长，每次减为原来的一半。
        for (int gap = length / 2; gap > 0; gap /= 2) {
            // 共gap个组，对每一组都执行直接插入排序
            for (int i = 0; i < gap; i++) {
                groupSort(array, length, i, gap);
            }
        }
    }

    public static void groupSort(int[] array, int length, int start, int gap) {
        for (int j = start + gap; j < length; j += gap) {
            // 如果a[j] < a[j-gap]，则寻找a[j]位置，并将后面数据的位置都后移。
            if (array[j] < array[j - gap]) {
                int tmp = array[j];
                int k = j - gap;
                while (k >= 0 && array[k] > tmp) {
                    array[k + gap] = array[k];
                    k -= gap;
                }
                array[k + gap] = tmp;
            }
        }
    }

}
