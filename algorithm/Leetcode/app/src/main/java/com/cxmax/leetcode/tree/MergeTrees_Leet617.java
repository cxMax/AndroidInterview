package com.cxmax.leetcode.tree;

/**
 * @describe :
 * @usage :
 * <p>
 *     617. 合并二叉树
 *     给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 * </p>
 * Created by caixi on 9/6/21.
 */
public class MergeTrees_Leet617 {

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // 非null判断
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        // 新节点
        TreeNode newRoot = new TreeNode(root1.val + root2.val);
        newRoot.left = mergeTrees(root1.left, root2.left);
        newRoot.right = mergeTrees(root1.right, root2.right);
        return newRoot;
    }

}
