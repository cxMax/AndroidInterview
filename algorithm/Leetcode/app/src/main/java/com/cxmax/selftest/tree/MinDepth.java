package com.cxmax.selftest.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * 111. 二叉树的最小深度
 *
 * https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
 *
 * Created by caixi on 2022/4/12.
 */
public class MinDepth {

    /**
     * 还是层序遍历， 跟最大深度有点不一样
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int minDepth = 0;
        while(!queue.isEmpty()) {
            int len = queue.size();
            minDepth++;
            for (int i = 0; i < len; i++) {
                TreeNode tmp = queue.poll();
                if (tmp.left == null && tmp.right == null) {
                    return minDepth;
                }
                if (tmp.left != null) {
                    queue.offer(tmp.left);
                }
                if (tmp.right != null) {
                    queue.offer(tmp.right);
                }
            }
        }
        return minDepth;
    }
}
