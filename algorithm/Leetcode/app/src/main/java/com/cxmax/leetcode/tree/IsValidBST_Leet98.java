package com.cxmax.leetcode.tree;

import java.util.Stack;

/**
 * @describe :
 * @usage :
 * <p>
 *     98. 验证二叉搜索树
 *     给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * </p>
 * Created by caixi on 9/8/21.
 */
public class IsValidBST_Leet98 {


    /**
     * 中序遍历先遍历出来
     * 这里要引入一个前节点
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return false;
        }
        // 中序遍历
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode pre = null;
        while(cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                // 回退到头节点， 这个时候遍历右指针
                cur = stack.pop();
                // 当前节点是右节点， 上一个节点是根节点， 根节点要小于右节点
                if (pre != null && cur.val <= pre.val) {
                    return false;
                }
                pre = cur;
                cur = cur.right;
            }
        }
        return true;

    }

}
