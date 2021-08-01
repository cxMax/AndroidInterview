package com.android.test1;

/**
 * @describe :
 * @usage :
 * <p>
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </p>
 * Created by caixi on 7/15/21.
 */
public class SpiralOrder_Offer29 {


    /**
     * 从左到右；从上至下；从右往左；从下往上
     * @param matrix
     * @return
     */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[0];
        }
        int[] result = new int[matrix.length * matrix[0].length];
        int index = 0;
        int top = 0;
        int down = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        while (true) {
            // 从左往右
            for (int i = left; i <= right ; ++i) {
                result[index++] = matrix[top][i];
            }
            if (++top > down) {
                break;
            }
            // 从上往下
            for (int i = top; i <= down; ++i) {
                result[index++] = matrix[i][right];
            }
            if (--right < left) {
                break;
            }
            // 从右往左
            for (int i = right; i >= left ; --i) {
                result[index++] = matrix[down][i];
            }
            if (--down < top) {
                break;
            }
            //  从下往上
            for (int i = down; i >= top; --i) {
                result[index++] = matrix[i][left];
            }
            if (++left > right) {
                break;
            }
        }
        return result;
    }
}
