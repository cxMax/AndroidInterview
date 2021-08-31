package com.cxmax.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 8/31/21.
 */
public class LevelOrder_Leet102 {

    /* 这里需要搞一个二维数组 */
    private List<List<Integer>> result =  new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        checkFun01(root, 0);
        return  result;
    }

    /**
     * DFS深度遍历优先 - 就是递归
     * 就是借用递归的方式来实现
     * todo 这个还没理解到， 还要多刷两遍
     * @param node
     * @param deep
     */
    public void checkFun01(TreeNode node, Integer deep) {
        if (node == null) {
            return;
        }
        deep++;
        if (result.size() < deep) {
            List<Integer> item = new ArrayList<>();
            result.add(item);
        }
        result.get(deep - 1).add(node.val);
        checkFun01(node.left, deep);
        checkFun01(node.right, deep);

    }

    /**
     * BFS广度遍历优先 - 就是循环
     * 借助队列来实现，
     * todo 还是没有理解到， 多刷两遍看看
     * @param node
     */
    public void checkFun02(TreeNode node) {
        // 借助队列来实现
        if (node == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        // 先把头节点加入进去

        queue.offer(node);

        while(!queue.isEmpty()) {
            List<Integer> itemList = new ArrayList<>();
            int len = itemList.size();

            while(len > 0) {
                // 先把头节点弹出
                TreeNode tmpNode = queue.poll();
                itemList.add(tmpNode.val);

                if (tmpNode.left != null) {
                    queue.offer(tmpNode.left);
                }
                if (tmpNode.right != null) {
                    queue.offer(tmpNode.right);
                }
                len--;
            }
            result.add(itemList);
        }


    }
}
