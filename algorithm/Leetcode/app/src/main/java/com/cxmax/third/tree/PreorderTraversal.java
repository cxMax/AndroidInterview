package com.cxmax.third.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 * <p>
 * 144. 二叉树的前序遍历
 * <p>
 * Created by caixi on 2022/7/29.
 */
public class PreorderTraversal {


    /**
     * 递归，深度遍历，dfs，
     *
     * 前序遍历 - 根、左、右
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traversal(root, result);
        return result;
    }

    private void traversal(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        result.add(node.val);
        traversal(node.left, result);
        traversal(node.right, result);
    }

}
