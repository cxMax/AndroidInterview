package com.cxmax.leetcode.tree;

/**
 * @describe :
 * @usage :
 * <p>
 *     701. 二叉搜索树中的插入操作
 * </p>
 * Created by caixi on 9/9/21.
 */
public class InsertIntoBST_Leet701 {


    public TreeNode insertIntoBST(TreeNode root, int val) {
        return buildTree(root, val);
    }

    /**
     * 还是利用二叉搜索树， 左 < 根 < 右
     * @param root
     * @param val
     * @return
     */
    public TreeNode buildTree(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val < val) {
            root.right = buildTree(root.right, val);
        } else if (root.val > val) {
            root.left = buildTree(root.left, val);
        }
        return root;
    }

}
