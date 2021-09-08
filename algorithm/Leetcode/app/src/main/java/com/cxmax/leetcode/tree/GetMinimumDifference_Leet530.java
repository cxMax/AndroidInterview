package com.cxmax.leetcode.tree;

import java.util.Stack;

/**
 * @describe :
 * @usage :
 * <p>
 *     530. 二叉搜索树的最小绝对差
 *     给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 *
 *     中序遍历的时候， 脑海里面一直想一下， 怎么遍历的
 *
 * </p>
 * Created by caixi on 9/8/21.
 */
public class GetMinimumDifference_Leet530 {

    // 中序遍历 来做 , 二叉搜索树， 中序遍历是有序的
    public int getMinimumDifference(TreeNode root) {
        int result = Integer.MAX_VALUE;
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode pre = null;
        while(cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                // 这里操作差值
                if (pre != null) {
                    result =  Math.min(result, Math.abs(pre.val - cur.val));
                }
                pre = cur;
                cur = cur.right;
            }
        }
        return result;
    }

}
