package com.cxmax.selftest.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

/**
 * https://leetcode-cn.com/problems/symmetric-tree/
 *
 * 101. 对称二叉树
 *
 * Created by caixi on 2022/3/14.
 */
public class IsSymmetric {

    /**
     * 第一种做法，使用递归来做，
     * 就是左边的左边， 是否等于，右边的右边
     * 比较笨，这种方法，直接列举出所有存在的可能情况。
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return compare(root.left, root.right);
    }

    /**
     * 直接列举所有的case 进行判断
     *
     * @param left
     * @param right
     * @return
     */
    public boolean compare(TreeNode left, TreeNode right) {
        if (left == null && right != null) {
            return false;
        }
        if (right == null && left != null) {
            return false;
        }
        if (right == null && left == null) {
            return true;
        }
        if (left != null && right != null && left.val != right.val) {
            return false;
        }
        boolean outer = compare(left.left, right.right);
        boolean inner = compare(left.right, right.left);
        return outer && inner;
    }

    /**
     * 遍历，使用双端队列来做
     * 感觉和递归的逻辑差不多
     *
     * @param root
     * @return
     */
    public boolean isSymmetric1(TreeNode root) {
        if (root == null) {
            return false;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offerFirst(root.left);
        queue.offerLast(root.right);
        while(!queue.isEmpty()) {
            TreeNode left = queue.pollFirst();
            TreeNode right = queue.pollLast();
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null || left.val != right.val) {
                return false;
            }
            // 必须按照对称来进队列
            queue.offerFirst(left.left);
            queue.offerFirst(left.right);
            queue.offerLast(right.right);
            queue.offerLast(right.left);
        }
        return queue.isEmpty();
    }

}
