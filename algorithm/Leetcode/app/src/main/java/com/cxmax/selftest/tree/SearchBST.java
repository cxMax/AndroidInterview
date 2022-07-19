package com.cxmax.selftest.tree;

import java.util.Stack;

/**
 * 700. 二叉搜索树中的搜索
 * <p>
 * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
 * <p>
 * 二叉搜索树是一个有序树：
 * <p>
 * 若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
 * <p>
 * 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值；
 * <p>
 * 它的左、右子树也分别为二叉搜索树
 * <p>
 * 这就决定了，二叉搜索树，递归遍历和迭代遍历和普通二叉树都不一样。
 * <p>
 * Created by caixi on 2022/5/24.
 */
public class SearchBST {

    /**
     * 使用的前序遍历， bfs来做的
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
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
