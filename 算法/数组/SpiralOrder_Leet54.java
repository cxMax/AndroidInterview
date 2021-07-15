package com.android.test1;

import java.util.LinkedList;
import java.util.List;

/**
 * @describe :
 * @usage :
 * <p>
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * <p>
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * </p>
 * Created by caixi on 7/14/21.
 */
public class SpiralOrder_Leet54 {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new LinkedList<>();
        if (matrix.length == 0) {
            return res;
        }
        int up = 0;
        int down = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        while (true) {
            // 从右往左
            for (int i = left; i <= right; ++i) {
                res.add(matrix[up][i]);
            }
            if (++up > down) {
                break;
            }
            // 从上往下
            for (int i = up; i <= down; ++i) {
                res.add(matrix[i][right]);
            }
            if (--right < left) {
                break;
            }
            // 从左往右
            for (int i = right; i >= left ; --i) {
                res.add(matrix[down][i]);
            }
            if (--down < up) {
                break;
            }
            // 从下往上
            for (int i = down; i >= up ; --i) {
                res.add(matrix[i][left]);
            }
            if (++left > right) {
                break;
            }
        }
        return res;
    }

}
