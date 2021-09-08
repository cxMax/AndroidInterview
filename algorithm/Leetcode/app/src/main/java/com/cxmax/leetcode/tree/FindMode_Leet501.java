package com.cxmax.leetcode.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @describe :
 * @usage :
 * <p>
 *     501. 二叉搜索树中的众数
 *     如果是树的话，用map来做， treemap key有序
 * </p>
 * Created by caixi on 9/8/21.
 */
public class FindMode_Leet501 {

    /**
     * 中序遍历，一定记住位置
     * @param root
     * @return
     */
    public int[] findMode(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        // 出现最大频次的数
        ArrayList<Integer> result = new ArrayList<>();
        int count = 0, maxCount = 0;
//        Map<Integer, Integer> map = new HashMap<>();
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (pre == null) { //第一个节点
                    count = 1;
                } else if (pre.val == cur.val) {
                    count++;
                } else {
                    count = 1;
                }
                // 如果都是一个频次
                if (count == maxCount) {
                    result.add(cur.val);
                }

                // 如果出现更高频次
                if (count > maxCount) {
                    maxCount = count;
                    result.clear();
                    result.add(cur.val);
                }


                pre = cur;
                cur = cur.right;
            }
        }
        int[] ret = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ret[i] = result.get(i);
        }
        return ret;
    }

}
