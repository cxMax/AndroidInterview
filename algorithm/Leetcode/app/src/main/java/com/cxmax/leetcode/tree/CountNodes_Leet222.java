package com.cxmax.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @describe :
 * @usage :
 * <p>
 *     222. 完全二叉树的节点个数
 * </p>
 * Created by caixi on 9/5/21.
 */
public class CountNodes_Leet222 {

    /**
     *  还是bfs来做
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        int result = 0;
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode tmp = queue.poll();
                //每次遍历，就是一个节点
                result++;
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
