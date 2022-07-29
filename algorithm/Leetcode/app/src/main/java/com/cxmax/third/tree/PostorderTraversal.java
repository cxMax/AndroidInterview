package com.cxmax.third.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 * <p>
 * 145. 二叉树的后序遍历
 * <p>
 * Created by caixi on 2022/7/29.
 */
public class PostorderTraversal {

    /**
     * 后续遍历， dfs，深度优先
     * 左、右、根
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traversal(root, result);
        return result;
    }

    private void traversal(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        traversal(node.left, result);
        traversal(node.right, result);
        result.add(node.val);
    }
}
