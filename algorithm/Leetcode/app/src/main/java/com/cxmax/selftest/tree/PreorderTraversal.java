package com.cxmax.selftest.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 * <p>
 * 144. 二叉树的前序遍历
 * <p>
 * Created by caixi on 2022/3/7.
 */
public class PreorderTraversal {


    /**
     * 递归实现， 前序 - 根、左、右， dfs 深度遍历
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        preOrder(root, result);
        return result;
    }

    private void preOrder(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        result.add(node.val);
        preOrder(node.left, result);
        preOrder(node.right, result);
    }

    /**
     * 遍历实现， bfs 广度优先
     * 入栈顺序， 根、右、左， 因为是stack， 入栈和出栈顺序，是先进后出
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // 使用stack来做
        Stack<TreeNode> stack = new Stack<>();
        // 记住、根、右、左
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode tmp = stack.pop();
            result.add(tmp.val);
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
