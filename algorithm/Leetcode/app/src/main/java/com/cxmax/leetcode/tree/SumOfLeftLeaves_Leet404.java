package com.cxmax.leetcode.tree;

import java.util.Stack;

/**
 * @describe :
 * @usage :
 * <p>
 *     404. 左叶子之和
 *     什么是叶子？ 左节点 - 没有左、右节点
 * </p>
 * Created by caixi on 9/6/21.
 */
public class SumOfLeftLeaves_Leet404 {

    /**
     * 先用bfs遍历出来所有节点
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
        // 前序遍历，先进后出
        while(!stack.isEmpty()) {
            TreeNode tmp = stack.pop();
            // 这个就是节点值
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
