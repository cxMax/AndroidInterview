package com.cxmax.third.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 111. 二叉树的最小深度
 *
 * https://leetcode.cn/problems/minimum-depth-of-binary-tree/
 *
 * Created by caixi on 2022/8/1.
 */
public class MinDepth {


    /**
     * 最小深度，就是左右孩子节点为null
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int deep = 0;
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode tmp = queue.poll();
                if (tmp.left != null) {
                    queue.offer(tmp.left);
                }
                if (tmp.right != null) {
                    queue.offer(tmp.right);
                }
                // 首次的时候，先++
                if (i  == 0) {
                    deep++;
                }
                // 在写出栈逻辑
                if (tmp.right == null && tmp.left == null) {
                    return deep;
                }
            }
        }
        return deep;

    }

}
