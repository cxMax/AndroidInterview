package com.cxmax.third.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 * <p>
 * 102. 二叉树的层序遍历
 * <p>
 * 输入：root = [3,9,20,null,null,15,7]
 * <p>
 * 输出：[[3],[9,20],[15,7]]
 * <p>
 * Created by caixi on 2022/7/31.
 */
public class LevelOrder {

    List<List<Integer>> result = new ArrayList<>();

    /**
     * 层序遍历， 还是好理解， 就是按照一层一层来，输出结果，结果是一个二维数组
     *
     * 一定记住按照一层一层的思想来
     * 1. 用到了双端队列
     * 2. 还是按照根、左、右来
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 每一层
            List<Integer> list = new ArrayList<>();
            // 每一层节点个数
            int len = queue.size();
            while(len > 0) {
                TreeNode tmp = queue.poll();
                list.add(tmp.val);
                // 这里因为是双端队列，先进先出，所以按顺序就行了
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
        return result;

    }
}
