package com.cxmax.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @describe :
 * @usage :
 * <p>
 *     94. 二叉树的中序遍历
 *     左 根 右
 * </p>
 * Created by caixi on 8/27/21.
 */
public class InorderTraversal_Leet94 {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    private void inOrder(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        inOrder(node.left, result);
        result.add(node.val);
        inOrder(node.right, result);
    }
}
