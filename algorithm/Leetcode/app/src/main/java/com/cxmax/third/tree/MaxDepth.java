package com.cxmax.third.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 104. 二叉树的最大深度
 * <p>
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * https://leetcode.cn/problems/maximum-depth-of-binary-tree/
 * <p>
 * Created by caixi on 2022/8/1.
 */
public class MaxDepth {

    /**
     * 最大深度，就是遍历到最尾部一层，deep++ 感觉就行了呢
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int deep = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
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
                // todo caixi 2022-8-1 这里做错了，每一层有几个节点，就加了几次
                if (i == 0) {
                    deep++;
                }
            }
        }
        return deep;

    }
}
