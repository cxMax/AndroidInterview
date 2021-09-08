package com.cxmax.leetcode.tree;

/**
 * @describe :
 * @usage :
 * <p>
 *     235. 二叉搜索树的最近公共祖先
 * </p>
 * Created by caixi on 9/9/21.
 */
public class LowestCommonAncestor_Leet235 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while(true) {
            if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else if (root.val < p.val && root.val < q.val) {
                root = root.right;
            } else {
                break;
            }
        }
        return root;
    }

}
