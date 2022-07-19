package com.cxmax.selftest.tree;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/path-sum/
 *
 * 112. 路径总和
 *
 * Created by caixi on 2022/4/26.
 */
public class HasPathSum {

    /**
     * 1. 使用前序遍历
     * 2. 使用两个stack来做
     *
     *
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        stack1.push(root);
        stack2.push(root.val);
        while(!stack1.isEmpty()) {
            TreeNode tmp = stack1.pop();
            int sum = stack2.pop();
            if (tmp.left == null && tmp.right == null && targetSum == sum) {
                return true;
            }
            if (tmp.right != null) {
                stack1.push(tmp.right);
                stack2.push(sum + tmp.right.val);
            }
            if (tmp.left != null) {
                stack1.push(tmp.left);
                stack2.push(sum + tmp.left.val);
            }
        }
        return false;
    }

}
