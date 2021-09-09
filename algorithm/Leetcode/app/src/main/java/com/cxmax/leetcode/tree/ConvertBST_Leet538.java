package com.cxmax.leetcode.tree;

import java.util.Stack;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 9/9/21.
 */
public class ConvertBST_Leet538 {

    /**
     * 使用中序遍历来做，中序，迭代的方式,
     * 然后，中序遍历， 就是 左 中 右， 先左节点， 然后在回溯
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        int pre = 0;
        while(cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.right;
            } else {
                cur = stack.pop();
                // 处理节点
                cur.val += pre;
                pre = cur.val;
                cur = cur.left;
            }
        }
        return root;
    }

}
