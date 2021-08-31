package com.cxmax.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    /**
     * 递归的实现， 就是左、中、右
     * @param root
     * @return
     */
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

    /**
     * 中序遍历的实现。
     * 核心就是， 先一路左节点走到底入栈，然后依次移除，在遍历右节点。
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()) {
            if (cur != null) {
                // 一直入栈最左边的节点
                stack.push(cur);
                cur = cur.left;
            } else {
                // 这个时候开始获取值
                cur = stack.pop();
                result.add(cur.val);
                // 这个时候在赋值右节点
                cur = cur.right;
            }
        }
        return result;
    }
}
