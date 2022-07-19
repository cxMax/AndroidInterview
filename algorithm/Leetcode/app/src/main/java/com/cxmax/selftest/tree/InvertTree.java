package com.cxmax.selftest.tree;


import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/invert-binary-tree/
 *
 * 226. 翻转二叉树
 *
 * Created by caixi on 2022/3/11.
 */
public class InvertTree {

    /**
     * 反转二叉树， 主要也是分递归和遍历，这两种做法
     * 前序遍历 和 后序遍历都行， 中序遍历不行
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
//        swapChild(root);
        invertTree(root.left);
        invertTree(root.right);
        swapChild(root);
        return root;
    }

    /**
     * 交换左右两个节点
     *
     * @param node
     */
    public void swapChild(TreeNode node) {
        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;
    }

    /**
     * 遍历方法来做，
     * 感觉跟前序、后序遍历是一样的写法
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
            swapChild(tmp);
            if (tmp.left != null) {
                stack.push(tmp.left);
            }
            if (tmp.right != null) {
                stack.push(tmp.right);
            }
        }
        return root;
    }
}
