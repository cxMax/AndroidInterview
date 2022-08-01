package com.cxmax.third.tree;

import java.util.Stack;

/**
 *
 * 226. 翻转二叉树
 *
 * https://leetcode.cn/problems/invert-binary-tree/
 *
 * Created by caixi on 2022/8/1.
 */
public class InvertTree {

    /**
     * 翻转二叉树，就是在遍历的时候，
     * 写法跟前序遍历类似， 固定写法
     * 这是递归的写法
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        sweepNode(root);
        if (root.left != null) {
            invertTree(root.left);
        }
        if (root.right != null) {
            invertTree(root.right);
        }
        return root;
    }

    /**
     * 交换该节点，左右两个节点
     * @param node
     */
    private void sweepNode(TreeNode node) {
        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;
    }


    /**
     * 这里写遍历的写法
     *
     * @param root
     * @return
     */
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) {
            return root;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode tmp = stack.pop();
            sweepNode(tmp);
            if (tmp.right != null) {
                stack.push(tmp.right);
            }
            if (tmp.left != null) {
                stack.push(tmp.left);
            }
        }
        return root;
    }


}
