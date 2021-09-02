package com.cxmax.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @describe :
 * @usage :
 * <p>
 *     637. 二叉树的层平均值
 *     其实这题也考察，二叉树的层序遍历
 * </p>
 * Created by caixi on 9/2/21.
 */
public class AverageOfLevels_Leet637 {

    /**
     * 还是会用到bfs广度遍历来做
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int len = queue.size();
            //  每一层的总和
            double sum = 0;
            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            // 每一层遍历完， 求和
            result.add(sum / len);
        }
        return result;
    }

}
