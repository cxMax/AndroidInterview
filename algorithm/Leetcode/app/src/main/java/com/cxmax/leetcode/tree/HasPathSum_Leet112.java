package com.cxmax.leetcode.tree;

import java.util.Stack;

/**
 * @describe :
 * @usage :
 * <p>
 *     112. 路径总和
 *     从根节点到子节点， 是否存在一条路径， 相加总和等于target
 * </p>
 * Created by caixi on 9/6/21.
 */
public class HasPathSum_Leet112 {


    /**
     * 前序遍历, 深度遍历来做， 一个路径一个路径遍历
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        // 用两个栈来做
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        stack1.push(root);
        stack2.push(root.val);
        while(!stack1.isEmpty()) {
            TreeNode tmp = stack1.pop();
            int sum = stack2.pop();
            // 判断退出条件， 达到叶子节点
            if (tmp.left == null && tmp.right == null && sum == targetSum) {
                return true;
            }

            // 前序遍历，先进后出
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
