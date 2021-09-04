package com.cxmax.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @describe :
 * @usage :
 * <p>
 *     199. 二叉树的右视图
 *     给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * </p>
 * Created by caixi on 9/2/21.
 */
public class RightSideView_Leet199 {


    public List<Integer> rightSideView(TreeNode root) {
        return bfs(root);
    }

    public List<Integer> bfs(TreeNode node) {
        List<Integer> item = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (node == null) {
            return item;
        }
        queue.offer(node);
        while(!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode tmp = queue.poll();
                if (tmp.left != null) {
                    queue.offer(tmp.left);
                }
                if (tmp.right != null) {
                    queue.offer(tmp.right);
                }
                if (i == len - 1) {
                    item.add(tmp.val);
                }
            }
        }
        return item;
    }
}
