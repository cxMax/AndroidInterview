package com.cxmax.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @describe :
 * @usage :
 * <p>
 *     100. 相同的树
 *     给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 * </p>
 * Created by caixi on 9/4/21.
 */
public class IsSameTree_Leet100 {

    /**
     * 我自己用bfs来写
     * 就是 左 = 左， 右 = 右， 根 = 根
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(p);
        queue.offer(q);
        while(!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null || left.val != right.val) {
                return false;
            }
            queue.offer(left.left);
            queue.offer(right.left);
            queue.offer(left.right);
            queue.offer(right.right);
        }
        return true;
    }

}
