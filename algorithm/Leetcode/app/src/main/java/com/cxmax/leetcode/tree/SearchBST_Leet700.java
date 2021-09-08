package com.cxmax.leetcode.tree;

import java.util.Stack;

/**
 * @describe :
 * @usage :
 * <p>
 *     700. 二叉搜索树中的搜索
 *     给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
 * </p>
 * Created by caixi on 9/8/21.
 */
public class SearchBST_Leet700 {

    /**
     * 使用的前序遍历， bfs来做的
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode tmp = stack.pop();
            if (tmp.val == val) {
                return tmp;
            }
            if (tmp.right != null) {
                stack.push(tmp.right);
            }
            if (tmp.left != null) {
                stack.push(tmp.left);
            }
        }
        return null;
    }

}
