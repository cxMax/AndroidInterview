package com.cxmax.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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


    /**
     * 这个是递归的形式来做
     * @param root
     * @return
     */
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

    /**
     * 采用遍历的方式来做
     * 根 左 右 , 入栈顺序 根 右 左， 先进后出
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        //  这个用堆栈来做
        Stack<TreeNode> stack = new Stack<>();
        // 先把根节点 push进去
        stack.push(root);
        // 开始遍历
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            //然后是右、右节点
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }

        }
        return result;
    }
}
