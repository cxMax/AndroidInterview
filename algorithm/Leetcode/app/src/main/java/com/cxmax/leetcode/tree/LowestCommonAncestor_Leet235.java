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

    /**
     * 如此简单的算法， 说真的， 木有看懂理解到， 哈哈哈， 只有死记硬背了
     * @param root
     * @param p
     * @param q
     * @return
     */
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
