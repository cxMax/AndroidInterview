package com.cxmax.third.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    /**
     *
     * 左、根、右，
     *
     * 1. 使用栈来做，入栈、出栈
     * 2. 一路左节点走到底
     * 3. 再添加
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        // 构造虚拟节点
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            // 一路到左
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                // todo caixi 2022-7-31 这里因为为nul了，所以要弹出stack里面的节点
                cur = stack.pop();
                // 左为null了
                result.add(cur.val);
                cur = cur.right;
            }
        }
        return result;
    }



}
