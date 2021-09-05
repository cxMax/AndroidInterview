package com.cxmax.leetcode.tree;

/**
 * @describe :
 * @usage :
 * <p>
 *     110. 平衡二叉树
 *     给定一个二叉树，判断它是否是高度平衡的二叉树。
 * </p>
 * Created by caixi on 9/5/21.
 */
public class IsBalanced_Leet110 {

    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }

    /**
     * 平衡二叉树， 左右节点高度差不能超过1
     *
     * -1 就表示不满足平衡规则
     * @param root
     * @return
     */
    public int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getHeight(root.left);
        if (left == -1) {
            return -1;
        }
        int right = getHeight(root.right);
        if (right == -1) {
            return -1;
        }
        //  高度差大于1 表示
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;

    }

}
