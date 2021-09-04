package com.cxmax.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @describe :
 * @usage :
 * <p>
 *     101. 对称二叉树
 *     给定一个二叉树，检查它是否是镜像对称的。
 * </p>
 * Created by caixi on 9/4/21.
 */
public class IsSymmetric_Leet101 {


    /**
     * dfs来做，
     * @param root
     * @return
     */
    public boolean isSymmetric1(TreeNode root) {
        return compare(root.left, root.right);
    }

    private boolean compare(TreeNode left, TreeNode right) {
        if (left != null && right == null) {
            return false;
        }
        if (left == null && right != null) {
            return false;
        }
        if (left == null && right == null) {
            return true;
        }
        if (left != null && right != null && left.val != right.val) {
            return false;
        }
        boolean outside = compare(left.left, right.right);
        boolean inside = compare(left.right, right.left);
        return outside && inside;
    }

    /**
     * LinkedList(普通队列， 双端队列)来做
     * @param root
     * @return
     */
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        while(!queue.isEmpty()) {
            // 先进先出
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null || left.val != right.val) {
                return false;
            }
            // 这里要按照比较顺序进行 队列添加
            queue.offer(left.left);
            queue.offer(right.right);
            queue.offer(left.right);
            queue.offer(right.left);
        }
        return true;
    }


}
