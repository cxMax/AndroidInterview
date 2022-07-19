package com.cxmax.selftest.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 100. 相同的树
 *
 * https://leetcode-cn.com/problems/same-tree/
 *
 *
 * Created by caixi on 2022/4/21.
 */
public class IsSameTree {

    /**
     * 使用层序遍历来做, 根、左、右
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(p);
        queue.offer(q);
        while(!queue.isEmpty()) {
          TreeNode left = queue.poll();
          TreeNode right = queue.poll();
          if (left == null && right == null) {
              continue;
          }
          if (left == null || right == null || left.val != right.val) {
              return false;
          }
          queue.offer(left.left);
          queue.offer(right.left);
          queue.offer(left.right);
          queue.offer(right.right);
        }
        return true;
    }

}
