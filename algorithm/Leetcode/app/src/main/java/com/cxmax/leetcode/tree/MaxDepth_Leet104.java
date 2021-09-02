package com.cxmax.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @describe :
 * @usage :
 * <p>
 *     104. 二叉树的最大深度
 *     给定一个二叉树，找出其最大深度。
 * </p>
 * Created by caixi on 9/3/21.
 */
public class MaxDepth_Leet104 {


    /**
     * 其实我理解就是层序遍历，  遍历多少层 就++
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
            int len = queue.size();
            // 每一层都会进入下一个循环
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
