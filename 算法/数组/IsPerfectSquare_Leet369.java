package com.android.test1;

/**
 * @describe :
 * @usage :
 * <p>
 * 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
 * <p>
 * 进阶：不要 使用任何内置的库函数，如  sqrt 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：num = 16
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：num = 14
 * 输出：false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-perfect-square
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </p>
 * Created by caixi on 6/25/21.
 */
public class IsPerfectSquare_Leet369 {


    /**
     *
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        if (num < 2) {
            return true;
        }
        int left = 0;
        int right = num / 2;
        while(left <= right) {
            int middle = left + ((right - left) >> 1);
            if (((long)middle * middle) > num) {
                right = middle - 1;
            } else if (((long)middle * middle) < num) {
                left = left + 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
