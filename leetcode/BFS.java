package com.art.editor.editor;

import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @describe :
 * @usage :
 * <p>
 * 广度优先搜索
 * 二叉树，从上自下遍历
 * </p>
 * Created by caixi on 12/22/20.
 */
public class BFS {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 这么一个二叉树
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     *
     * @param args
     */
    public static void main(String[] args) {
        TreeNode left = new TreeNode(9);
        TreeNode top = new TreeNode(3);
        TreeNode right = new TreeNode(20);
        TreeNode left1 = new TreeNode(15);
        TreeNode right1 = new TreeNode(7);
        top.left = left;
        top.right = right;
        right.left = left1;
        right.right = right1;
        int[] result = bfs(top);
        for (int i = 0; i < result.length; i++) {
            Log.e("info", "main: " + result[i]);
        }
    }
 
    public static int[] bfs(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> data = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            data.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        int[] result = new int[data.size()];
        if (!data.isEmpty()) {
            for (int i = 0; i < data.size(); i++) {
                result[i] = data.get(i);
            }
        }
        return result;
    }
}
