package com.art.editor.editor;

import android.util.Log;

/**
 * @describe :
 * @usage :
 * <p>
 *      二维数组的快速查找某个数，看是否包含该数字
 *      核心，就是从左下角开始搜索、比较
 * </p>
 * Created by caixi on 12/23/20.
 */
public class FindNumberIn2DArray {

    /**
     * [
     * [1,   4,  7, 11, 15],
     * [2,   5,  8, 12, 19],
     * [3,   6,  9, 16, 22],
     * [10, 13, 14, 17, 24],
     * [18, 21, 23, 26, 30]
     * ]
     *
     * @param args
     */
    public static void main(String[] args) {
        int[][] matrix = new int[5][5];
        matrix[0][0] = 1;
        matrix[0][1] = 4;
        matrix[0][2] = 7;
        matrix[0][3] = 11;
        matrix[0][4] = 15;
        matrix[1][0] = 2;
        matrix[1][1] = 5;
        matrix[1][2] = 8;
        matrix[1][3] = 12;
        matrix[1][4] = 19;
        matrix[2][0] = 3;
        matrix[2][1] = 6;
        matrix[2][2] = 9;
        matrix[2][3] = 16;
        matrix[2][4] = 22;
        matrix[3][0] = 10;
        matrix[3][1] = 13;
        matrix[3][2] = 14;
        matrix[3][3] = 17;
        matrix[3][4] = 24;
        matrix[4][0] = 18;
        matrix[4][1] = 21;
        matrix[4][2] = 23;
        matrix[4][3] = 26;
        matrix[4][4] = 30;
        Log.e("info", "main: " + findNumberIn2DArray(matrix, 8));
    }

    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        int i = matrix.length - 1;
        int j = 0;
        while (i >= 0 && j < matrix[0].length) {
            if (matrix[i][j] > target) {
                i--;
            } else if (matrix[i][j] < target) {
                j++;
            } else {
                return true;
            }
        }
        return false;
    }

}
