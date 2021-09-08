package com.cxmax.leetcode.tree;

/**
 * @describe :
 * @usage :
 * <p>
 *     236. 二叉树的最近公共祖先
 *     给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * </p>
 * Created by caixi on 9/8/21.
 */
public class LowestCommonAncestor_Leet236 {

    /**
     * 后序遍历，本身也是一次回溯
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.left, p, q);
        if (left != null && right != null) {
            return root;
        }

        if (left == null && right != null) {
            return right;
        } else if (left != null && right == null) {
            return left;
        } else {
            return null;
        }
    }

}
