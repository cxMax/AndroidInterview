package com.cxmax.selftest.tree;

import com.cxmax.leetcode.tree.TreeNode;

/**
 * https://leetcode-cn.com/problems/balanced-binary-tree/
 *
 * 110. 平衡二叉树
 *
 * Created by caixi on 2022/4/13.
 */
public class IsBalanced {

    /**
     * 一棵高度平衡二叉树定义为：一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }

    /**
     * 说实话，这个递归，理解一般，后序遍历，左、右根
     * @param root
     * @return 如果返回是-1，就表示，不是高度平衡的二叉树
     */
    public int getHeight(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int left = getHeight(root.left);
        // 表示不是平衡二叉树
        if (left == -1) {
            return -1;
        }
        int right = getHeight(root.right);
        if (right == -1) {
            return -1;
        }
        // 左右高度差大雨1，就表示不是高度平衡的二叉树
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }
}
