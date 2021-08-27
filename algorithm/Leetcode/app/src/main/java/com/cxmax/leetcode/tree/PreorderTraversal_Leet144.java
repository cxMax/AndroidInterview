package com.cxmax.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @describe :
 * @usage :
 * <p>
 *     144. 二叉树的前序遍历
 *     前序遍历 就是 - 根，左，右 递归方式
 * </p>
 * Created by caixi on 8/27/21.
 */
public class PreorderTraversal_Leet144 {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preOrder(root, result);
        return result;
    }

    void preOrder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        preOrder(root.left, result);
        preOrder(root.right, result);

    }

}
