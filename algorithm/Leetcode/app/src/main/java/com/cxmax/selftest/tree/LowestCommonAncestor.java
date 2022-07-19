package com.cxmax.selftest.tree;

/**
 * 236. 二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
 *
 * Created by caixi on 2022/5/30.
 */
public class LowestCommonAncestor {

    /**
     * 1. 后序遍历
     * 2. 递归
     * 3. 固定模版
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 异常处理
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 左、右都不为空，那就是公共祖先
        if (left != null && right != null) {
            return root;
        }
        // 固定算法模板
        if (left != null && right == null) {
            return left;
        } else if (left == null && right != null) {
            return right;
        } else {
            return null;
        }
    }
}
