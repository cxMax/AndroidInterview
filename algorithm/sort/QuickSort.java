package com.android.test1.sort;

/**
 * @describe :
 * @usage :
 * <p>
 *     快速排序 :
 *     reference : https://www.cnblogs.com/xiaoming0601/p/5862860.html
 *     快速排序是对冒泡排序的一种改进，平均时间复杂度是O(nlogn)
 * </p>
 * Created by caixi on 8/8/21.
 */
public class QuickSort {

    public static void quickSort(int[] array) {
        quickSort(array, 0 , array.length - 1);
    }

    private static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int mid = getMiddle(array, low, high);
            quickSort(array, low, mid -1);
            quickSort(array, mid + 1, high);
        }

    }

    private static int getMiddle(int[] array, int low, int high) {
        int tmp = array[low]; //数组的第一个作为中轴
        while(low < high) {
            while(low < high && array[high] >= tmp) {
                high--;
            }
            array[low] = array[high]; //比中轴小的记录移到低端
            while (low < high && array[low] <= tmp) {
                low++;
            }
            array[high] = array[low]; //比中轴大的记录移到高端
        }
        array[low] = tmp; //中轴记录到尾
        return low; //返回中轴的位置
    }

}
