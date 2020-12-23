package com.art.editor.editor;

import android.util.Log;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @describe :
 * @usage :
 * <p>
 *     找出数组中，重复的数字
 * </p>
 * Created by caixi on 12/23/20.
 */
public class FindRepeatNumber {

    public static void main(String[] args) {
        int[] a = new int[]{2, 3, 1, 0, 2, 5, 3};
        int result = findRepeatNumber(a);
        Log.e("info", "main: " + result);
    }

    /**
     * 这个算法， 如果是两位数、三位数就有问题了
     * @param a
     * @return
     */
    public static int findRepeatNumber(int[] a) {
        if (a.length == 0) {
            return -1;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i]);
        }
        Log.e("info", "findRepeatNumber: " + sb.toString());
        String content = sb.toString();
        for (int i = 0; i < content.length(); i++) {
            char s = content.charAt(i);
            int first = content.indexOf(s);
            int last = content.lastIndexOf(s);
            if (first != last) {
                return Integer.parseInt(String.valueOf(content.charAt(i)));
            }
        }
        return -1;
    }

    public static int findRepeatNumber1(int[] a) {
        if (a.length == 0) {
            return -1;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            map.put(a[i], map.getOrDefault(a[i], 0) + 1);
        }
        for (int i = 0; i < a.length; i++) {
            if (map.get(a[i]) != 1) {
                return a[i];
            }
        }
        return -1;
    }

    public static int findRepeatNumber2(int[] a) {
        if (a.length == 0) {
            return -1;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < a.length; i++) {
            if (!set.add(a[i])) {
                return a[i];
            }
        }
        return -1;
    }
}
