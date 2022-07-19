package com.cxmax.selftest.tree;

import java.util.Stack;

/**
 * 98. 验证二叉搜索树
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * Created by caixi on 2022/5/25.
 */
public class IsValidBST {

    /**
     * 二叉搜索树，就是有序，左、根、右 递增
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 1. 使用迭代法来进行遍历
        Stack<TreeNode> stack = new Stack<>();
        // 因为要比较大小，所以要定义两个虚拟节点进行大小比较
        TreeNode cur = root;
        TreeNode prev = null;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                // 从二叉树的最左节点开始遍历，
                stack.push(cur);
                cur = cur.left;
            } else {
                // 回退节点，这里是遍历右指针
                cur = stack.pop();
                // cur是右节点，prev是根节点，根节点一定要小于右节点
                if (prev != null && cur.val <= prev.val) {
                    return false;
                }
                prev = cur;
                cur = cur.right;
            }
        }
        return true;
    }
}
