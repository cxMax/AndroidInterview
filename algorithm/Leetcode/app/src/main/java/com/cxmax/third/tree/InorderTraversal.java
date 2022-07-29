package com.cxmax.third.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 * <p>
 * 94. 二叉树的中序遍历
 * <p>
 * Created by caixi on 2022/7/29.
 */
public class InorderTraversal {

    /**
     * 中序遍历， dfs， 左、根、右
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traversal(root, result);
        return result;
    }

    private void traversal(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        traversal(node.left, result);
        result.add(node.val);
        traversal(node.right, result);
    }
}
