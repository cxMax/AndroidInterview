package com.cxmax.selftest.tree;

/**
 * 701. 二叉搜索树中的插入操作
 * https://leetcode.cn/problems/insert-into-a-binary-search-tree/
 *
 * Created by caixi on 2022/5/30.
 */
public class InsertIntoBST {

    /**
     * 感觉这道题，也是用递归做，就更简单一些
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        } else if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }
}
