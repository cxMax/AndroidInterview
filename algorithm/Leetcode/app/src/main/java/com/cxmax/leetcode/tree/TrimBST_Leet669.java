package com.cxmax.leetcode.tree;

/**
 * @describe :
 * @usage :
 * <p>
 *     669. 修剪二叉搜索树
 *     给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。
 * </p>
 * Created by caixi on 9/9/21.
 */
public class TrimBST_Leet669 {

    /**
     * 感觉也是 根、 左 、 右的方式递归呢
     * @param root
     * @param low
     * @param high
     * @return
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        // 根节点，小于最小区间， 那就看比他大的右节点
        if (root.val < low) {
            return trimBST(root.right, low, high);
        }
        // 那就看比他小的 左节点
        if (root.val > high) {
            return trimBST(root.left, low, high);
        }
        // 再确定左、右两个节点
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }

}
