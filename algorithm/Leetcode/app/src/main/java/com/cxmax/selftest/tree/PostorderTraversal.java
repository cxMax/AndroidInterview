package com.cxmax.selftest.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 * <p>
 * 145. 二叉树的后序遍历
 * <p>
 * Created by caixi on 2022/3/7.
 */
public class PostorderTraversal {

    /**
     * dfs, 递归实现， 深度优先遍历算法，
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        postorder(root, result);
        return result;
    }

    /**
     * 后序遍历，左、右、根
     * @param node
     * @param result
     */
    private void postorder(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        postorder(node.left, result);
        postorder(node.right, result);
        result.add(node.val);
    }

    /**
     * 通过遍历去实现， bfs，广度优先遍历算法
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        // 记住， 左、右、根，要因此倒推
        // 这里要用到栈的特性，所以是 右、左、根，
        // 在反转， 所以， 顺序是根、左、右
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode tmp = stack.pop();
            result.add(tmp.val);
            if (tmp.left != null) {
                stack.push(tmp.left);
            }
            if (tmp.right != null) {
                stack.push(tmp.right);
            }
        }
        Collections.reverse(result);
        return result;
    }
}
