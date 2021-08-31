package com.cxmax.leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @describe :
 * @usage :
 * <p>
 *     从下往上遍历， 我觉得， 就是用到数组反转不就好啦
 * </p>
 * Created by caixi on 8/31/21.
 */
public class LevelOrderBottom_Leet107 {

    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
//        dfs(root, 0);
        bfs(root);
        Collections.reverse(result);
        return result;
    }


    /**
     * 深度就是递归
     * @param node
     * @param deep
     */
    private void dfs(TreeNode node, int deep) {
        if (node == null) {
            return;
        }
        deep++;
        if (result.size() < deep) {
            List<Integer> item = new ArrayList<>();
            result.add(item);
        }
        result.get(deep - 1).add(node.val);
        if (node.left != null) {
            dfs(node.left, deep);
        }
        if (node.right != null) {
            dfs(node.right, deep);
        }
    }

    /**
     * 广度遍历
     * @param node
     */
    private void bfs(TreeNode node) {
        if (node == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);

        while(!queue.isEmpty()) {
            List<Integer> item = new ArrayList<>();
            int len = queue.size();

            while (len > 0) {
                TreeNode tmp = queue.poll();
                item.add(tmp.val);
                if (tmp.left != null) {
                    queue.offer(tmp.left);
                }
                if (tmp.right != null) {
                    queue.offer(tmp.right);
                }
                len--;
            }
            result.add(item);
        }
    }

}
