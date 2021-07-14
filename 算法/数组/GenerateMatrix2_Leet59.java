package com.android.test1;

/**
 * @describe :
 * @usage :
 * <p>
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * </p>
 * Created by caixi on 7/14/21.
 */
public class GenerateMatrix2_Leet59 {


    /**
     * 从外圈向内部循环, 依次遍历上、右、下、左
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] result  = new int[n][n];
        // 循环次数
        int loop = n / 2;
        // 每次遍历的初始值
        int startX = 0;
        int startY = 0;
        // 定义偏移量
        int offset = 1;
        // 定义填充数字
        int count = 1;
        // 定义中间位置
        int mid = n / 2;
        while(loop > 0) {
            int i = startX;
            int j = startY;
            // 最上面，从左往右;
            for (; j < startY + n - offset; j++) {
                result[i][j] = count++;
            }
            // 最右边， 从上往下
            for (; i < startX + n - offset; i++) {
                result[i][j] = count++;
            }
            // 最下边， 从左往右
            for (;j > startY; j--) {
                result[i][j] = count++;
            }
            // 最左边， 从下往上
            for (; i > startX ; i++) {
                result[i][j] = count++;
            }
            // 第一圈循环完了，先循环次数-1
            loop--;
            startX++;
            startY++;
            //没循环一圈，左右偏移量各增加1
            offset += 2;
        }
        // 确定中点
        if (n % 2 == 1) {
            result[mid][mid] = count;
        }
        return result;
    }

}
