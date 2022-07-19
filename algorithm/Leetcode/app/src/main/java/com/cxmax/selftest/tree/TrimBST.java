package com.cxmax.selftest.tree;

/**
 *
 * 669. 修剪二叉搜索树
 * 给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。
 *
 * https://leetcode.cn/problems/trim-a-binary-search-tree/
 *
 * Created by caixi on 2022/5/31.
 */
public class TrimBST {

    /**
     * 感觉跟二叉树的删除节点元素很像，
     * 我感觉也是固定模板的写法
     *
     * 前序遍历的写法
     *
     * @param root
     * @param low
     * @param high
     * @return
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        // 根节点，小于低位，就用右节点去比较
        if (root.val < low) {
            return trimBST(root.right, low, high);
        }
        // 根节点，比高位还大，就用左节点
        if (root.val > high) {
            return trimBST(root.left, low, high);
        }
        // 分别确定左右两个节点
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }

}
