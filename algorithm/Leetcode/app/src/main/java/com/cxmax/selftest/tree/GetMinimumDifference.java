package com.cxmax.selftest.tree;

import java.util.Stack;

/**
 * 530. 二叉搜索树的最小绝对差
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 *
 * notice : 我感觉二叉搜索树的中序遍历真的是模版写法
 *
 * Created by caixi on 2022/5/25.
 */
public class GetMinimumDifference {

    /**
     * 中序遍历 来做 , 二叉搜索树， 中序遍历是有序的
     * 二叉搜索树，一下就想到中序遍历是有序的
     * 遇到在二叉搜索树上求什么最值啊，差值之类的，就把它想成在一个有序数组上求最值，求差值，这样就简单多了。
     *
     * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {
        // 先写中序遍历的模版写法
        int result = Integer.MAX_VALUE;
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode prev = null;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                // 这里进行比较
                if (prev != null) {
                    result = Math.min(result, cur.val - prev.val);
                }
                // prev 是根， cur是右节点
                prev = cur;
                cur = cur.right;
            }
        }
        return result;
    }
}
