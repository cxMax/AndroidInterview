package com.android.test1.sort;

/**
 * @describe :
 * @usage :
 * <p>
 *     基数排序
 *     https://www.cnblogs.com/Braveliu/archive/2013/01/21/2870201.html
 *     https://www.cnblogs.com/haozhengfei/p/29ba40edbf659f2dbc6b429c2818c594.html
 * </p>
 * Created by caixi on 8/8/21.
 */
public class RadixSort {

    public static void radixSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        int length = arr.length;
        int divisor = 1;// 定义每一轮的除数，1,10,100...
        int[][] bucket = new int[10][length];// 定义了10个桶，以防每一位都一样全部放入一个桶中
        int[] count = new int[10];// 统计每个桶中实际存放的元素个数
        int digit;// 获取元素中对应位上的数字，即装入那个桶
        for (int i = 1; i <= 3; i++) {// 经过4次装通操作，排序完成
            for (int temp : arr) {// 计算入桶
                digit = (temp / divisor) % 10;
                bucket[digit][count[digit]++] = temp;
            }
            int k = 0;// 被排序数组的下标
            for (int b = 0; b < 10; b++) {// 从0到9号桶按照顺序取出
                if (count[b] == 0)// 如果这个桶中没有元素放入，那么跳过
                    continue;
                for (int w = 0; w < count[b]; w++) {
                    arr[k++] = bucket[b][w];
                }
                count[b] = 0;// 桶中的元素已经全部取出，计数器归零
            }
            divisor *= 10;
        }
    }
}
