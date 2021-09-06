package com.cxmax.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @describe :
 * @usage :
 * <p>
 *     513. 找树左下角的值
 *     给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
 * </p>
 * Created by caixi on 9/6/21.
 */
public class FindBottomLeftValue_Leet513 {

    /**
     * 先写出层序遍历
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        int result = 0;
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int len = queue.size();
            // 每次遍历每一层
            for (int i = 0; i < len; i++) {
                TreeNode tmp = queue.poll();
                if (i == 0) {
                    result = tmp.val;
                }
                if (tmp.left != null) {
                    queue.offer(tmp.left);
                }
                if (tmp.right != null) {
                    queue.offer(tmp.right);
                }
            }
        }
        return result;
    }
}
