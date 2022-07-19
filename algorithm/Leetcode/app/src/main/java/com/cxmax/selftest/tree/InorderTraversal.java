package com.cxmax.selftest.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 * <p>
 * 94. 二叉树的中序遍历
 * <p>
 * Created by caixi on 2022/3/7.
 */
public class InorderTraversal {

    /**
     * 使用递归来做， 递归就很简单，中序遍历 就是 左 、 中 、右
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    /**
     * 递归，中序遍历
     * @param node
     * @param result
     */
    private void inorder(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        inorder(node.left, result);
        result.add(node.val);
        inorder(node.right, result);
    }

    /**
     * 通过遍历的方式打印
     *
     * 1. 使用栈来做，入栈、出栈
     * 2. 一路左节点走到底
     * 3. 再添加
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // 其实这里也可以用双端队列来做
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                result.add(cur.val);
                cur = cur.right;
            }
        }
        return result;
    }

}
