package com.android.test1.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @describe :
 * @usage :
 * <p>
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 * <p>
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。
 * <p>
 * 例如:
 * <p>
 * 输入:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 * <p>
 * 输出:
 * 2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。ø
 * </p>
 * Created by caixi on 7/21/21.
 */
public class FourSumCount_Leet454 {

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> sum = new HashMap<>();
        // 4个数字相加等于0的次数
        int ret = 0;
        // 一次拆分成，两两相加
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int tmp = nums1[i] + nums2[j];
                if (sum.containsKey(tmp)) {
                    sum.put(tmp, sum.get(tmp) + 1);
                } else {
                    sum.put(tmp, 1);
                }
            }
        }
        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                int tmp = nums3[i] + nums4[j];
                if (sum.containsKey(-tmp)) {
                    ret += sum.get(-tmp);
                }
            }
        }
        return ret;
    }

}
