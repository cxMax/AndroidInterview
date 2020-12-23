package com.art.editor.editor;

import android.util.Log;

/**
 * @describe :
 * @usage :
 * <p>
 * 正负数，排序，正数在右边，负数在左边
 * </p>
 * Created by caixi on 12/22/20.
 */
public class Sort_Positive_Negative {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, -3, -4, 5, 6, -7, -8};
        int left = 0;
        int right = nums.length - 1;
        setParted(nums, left, right);
    }


    public static void setParted(int[] a, int left, int right) {
        if (left >= right || left == a.length || right == 0) {
            for (int i = 0; i < a.length; i++) {
                Log.e("info", "setParted: " + a[i]);
            }
            return;
        }
        while (a[left] < 0) {
            left++;
        }
        while (a[right] >= 0) {
            right--;
        }
        if (left >= right || left == a.length || right == 0) {
            for (int i = 0; i < a.length; i++) {
                Log.e("info", "setParted: " + a[i]);
            }
            return;
        }
        swap(a, left, right);
        left++;
        right--;
        setParted(a, left, right);
    }


    /**
     * 交换顺序
     *
     * @param a
     * @param left
     * @param right
     */
    public static void swap(int[] a, int left, int right) {
        int temp = 0;
        temp = a[left];
        a[left] = a[right];
        a[right] = temp;
    }

}
