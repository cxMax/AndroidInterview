package com.cxmax.selftest.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * https://leetcode-cn.com/problems/find-bottom-left-tree-value/
 *
 * 513. 找树左下角的值
 *
 * Created by caixi on 2022/4/26.
 */
public class FindBottomLeftValue {

    /**
     * 1. 使用层序遍历来做
     * 2. 记录最后一行第一个元素
     *
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        int res = 0;
        if (root == null) {
            return res;
        }
        // 层序遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode tmp = queue.poll();
                if (i == 0) {
                    res = tmp.val;
                }
                if (tmp.left != null) {
                    queue.offer(tmp.left);
                }
                if (tmp.right != null) {
                    queue.offer(tmp.right);
                }
            }
        }
        return res;
    }

}
