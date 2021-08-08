package com.android.test1.sort;

/**
 * @describe :
 * @usage :
 * <p>
 *     归并排序 : $O(nlogn)$
 *     reference : https://www.cnblogs.com/shudonghe/p/3302888.html
 * </p>
 * Created by caixi on 8/8/21.
 */
public class MergeSort {

    public static void mergeSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        sort(array, 0, array.length - 1);
    }

    private static void sort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = (left + right) / 2;
        sort(array, left, mid);
        sort(array, mid + 1, right);

        merge(array, left, mid, right);
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int[] temp = new int[array.length];
        int r1 = mid + 1;
        int tIndex = left;
        int cIndex = left;

        while (left <= mid && r1 <= right) {
            if (array[left] <= array[r1]) {
                temp[tIndex++] = array[left++];
            } else {
                temp[tIndex++] = array[r1++];
            }
        }

        while (left <= mid) {
            temp[tIndex++] = array[left++];
        }

        while(r1 <= right) {
            temp[tIndex++] = array[r1++];
        }

        while(cIndex <= right) {
            array[cIndex] = temp[cIndex];
            cIndex++;
        }
    }
}
