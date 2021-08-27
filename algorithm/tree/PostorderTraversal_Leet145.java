package com.cxmax.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @describe :
 * @usage :
 * <p>
 *     145. 二叉树的后序遍历
 *     左 右 根
 * </p>
 * Created by caixi on 8/27/21.
 */
public class PostorderTraversal_Leet145 {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postOrder(root, result);
        return result;
    }

    private void postOrder(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        postOrder(node.left, result);
        postOrder(node.right, result);
        result.add(node.val);
    }
}
