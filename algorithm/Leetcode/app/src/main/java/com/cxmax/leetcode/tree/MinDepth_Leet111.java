package com.cxmax.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 9/3/21.
 */
public class MinDepth_Leet111 {

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int minDepth = 0;
        while(!queue.isEmpty()) {
            int len = queue.size();
            // 每一次进入循环，都是一层
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
