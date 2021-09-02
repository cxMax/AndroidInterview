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
 *     515. 在每个树行中找最大值
 *     给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
 * </p>
 * Created by caixi on 9/2/21.
 */
public class LargestValues_Leet515 {

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int len = queue.size();
            // 这个是每一层
            List<Integer> max = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                TreeNode tmp = queue.poll();
                max.add(tmp.val);
                if (tmp.left != null) {
                    queue.offer(tmp.left);
                }
                if (tmp.right != null) {
                    queue.offer(tmp.right);
                }
            }
            result.add(Collections.max(max));
        }
        return result;

    }

}
