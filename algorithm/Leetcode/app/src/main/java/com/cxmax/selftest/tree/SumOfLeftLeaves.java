package com.cxmax.selftest.tree;


import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/sum-of-left-leaves/
 *
 * 404. 左叶子之和
 *
 * Created by caixi on 2022/4/21.
 */
public class SumOfLeftLeaves {

    /**
     * 这道题主要是2点，
     * 1. 前序、遍历，使用bfs 没有用递归
     * 2. 判断是否是左叶子节点， left != null, left.left == null, left.right == null
     *
     * notice :
     * 1. 二叉树遍历，使用stack
     * 2. 二叉树层序遍历，使用LinkedList
     *
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        int result = 0;
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            // 前序遍历，根、右、左，因为是stack嘛
            TreeNode tmp = stack.pop();
            if (tmp.left != null && tmp.left.left == null && tmp.left.right == null) {
                result += tmp.left.val;
            }
            if (tmp.right != null) {
                stack.push(tmp.right);
            }
            if (tmp.left != null) {
                stack.push(tmp.left);
            }
        }
        return result;
    }
}
