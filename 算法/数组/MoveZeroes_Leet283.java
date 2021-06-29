package com.android.test1;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 6/29/21.
 */
public class MoveZeroes_Leet283 {

    /**
     * 元素移动， 双指针法
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int slowIndex = 0;
        int zeroCount = 0;
        for (int fastIndex = 0; fastIndex < nums.length; fastIndex++) {
            if (nums[fastIndex] != 0) {
                nums[slowIndex++] = nums[fastIndex];
            } else {
                zeroCount++;
            }
        }

        for (int i = nums.length - zeroCount; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

}
