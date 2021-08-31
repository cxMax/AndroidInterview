package com.cxmax.leetcode.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @describe :
 * @usage :
 * <p>
 *     145. 二叉树的后序遍历
 *     左 右 根
 * </p>
 * Created by caixi on 8/27/21.
 */
public class PostorderTraversal_Leet145 {

    /**
     * 递归的实现， 后序遍历就是 左、右、根
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postOrder(root, result);
        return result;
    }

    private void postOrder(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        postOrder(node.left, result);
        postOrder(node.right, result);
        result.add(node.val);
    }


    /**
     * 遍历的实现， 这个比较巧妙。 因为会用到栈来实现， 左 右 中， 前序遍历是， 中 左 右 ， 所以会用到数组反转，期望是 中 右 左
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // 前序遍历 ，就是根节点先
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        Collections.reverse(result);
        return result;
    }
}
