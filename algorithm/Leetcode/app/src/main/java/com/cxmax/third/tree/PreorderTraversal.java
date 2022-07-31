package com.cxmax.third.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    /**
     * 遍历实现前序遍历
     *
     * 1. 还是记得，根、左、右，
     * 2. 使用栈来解决
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // 这里可以用LinkedList
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode tmp = stack.pop();
            result.add(tmp.val);
            // 为什么这么写，栈先进后出
            if (tmp.right != null) {
                stack.push(tmp.right);
            }
            if (tmp.left != null) {
                stack.push(tmp.left);
            }
        }
        return result;
    }
}
