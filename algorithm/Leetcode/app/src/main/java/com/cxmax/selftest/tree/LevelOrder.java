package com.cxmax.selftest.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 * <p>
 * 102. 二叉树的层序遍历
 * <p>
 * Created by caixi on 2022/3/9.
 */
public class LevelOrder {

    // 结果是一个二维数组
    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        checkFun01(root, 0);
        return result;
    }

    /**
     * 递归实现，层序遍历，dfs
     *
     * @param node
     * @param deep
     */
    public void checkFun01(TreeNode node, int deep) {
        if (node == null) {
            return;
        }
        // 先层级增加
        deep++;
        // 每到新的一层，都需要新建一个集合
        if (result.size() < deep) {
            List<Integer> list = new ArrayList<>();
            result.add(list);
        }
        // 就想象曾第一层，添加完集合，接下来就是要把这层的值加进去
        result.get(deep - 1).add(node.val);
        // 然后左、右 顺序递归
        checkFun01(node.left, deep);
        checkFun01(node.right, deep);
    }

    /**
     * 使用遍历来实现，bfs， 需要用到队列来实现
     *
     * @param node
     */
    public void checkFun01(TreeNode node) {
        if (node == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);

        while(!queue.isEmpty()) {
            // 这个是每一层
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
            // 每一次把该层遍历完，要加入到结果中去
            result.add(list);
        }
    }

}
