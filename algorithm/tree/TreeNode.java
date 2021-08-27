package com.cxmax.leetcode.tree;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 8/27/21.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
