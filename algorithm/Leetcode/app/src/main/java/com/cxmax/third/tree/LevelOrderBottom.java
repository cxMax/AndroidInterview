package com.cxmax.third.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/binary-tree-level-order-traversal-ii/
 * <p>
 * 107. 二叉树的层序遍历 II
 * <p>
 * 输入：root = [3,9,20,null,null,15,7]
 * <p>
 * 输出：[[15,7],[9,20],[3]]
 * <p>
 * Created by caixi on 2022/7/31.
 */
public class LevelOrderBottom {

    List<List<Integer>> result = new ArrayList<>();

    /**
     * 和102一样， 还是层序遍历，只是直接使用数组反转，
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int len = queue.size();
            while (len > 0) {
                TreeNode tmp = queue.poll();
                list.add(tmp.val);
                if (tmp.left != null) {
                    queue.offer(tmp.left);
                }
                if (tmp.right != null) {
                    queue.offer(tmp.right);
                }
                len--;
            }
            result.add(list);
        }
        Collections.reverse(result);
        return result;
    }
}
