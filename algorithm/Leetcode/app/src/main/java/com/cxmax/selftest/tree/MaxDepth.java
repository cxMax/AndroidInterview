package com.cxmax.selftest.tree;


import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * 104. 二叉树的最大深度
 *
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 *
 * Created by caixi on 2022/4/8.
 */
public class MaxDepth {


    /**
     * 使用层序遍历，返回深度
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int size = 0;
        while(!queue.isEmpty()) {
            // 这里就是每一层了
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode tmp = queue.poll();
                if (tmp.left != null) {
                    queue.offer(tmp.left);
                }
                if (tmp.right != null) {
                    queue.offer(tmp.right);
                }
                if (i == 0) {
                    size++;
                }
            }
        }
        return size;
    }
}
