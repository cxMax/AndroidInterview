package com.cxmax.third.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 515. 在每个树行中找最大值
 * <p>
 * https://leetcode.cn/problems/find-largest-value-in-each-tree-row/
 * <p>
 * 输入: root = [1,3,2,5,3,null,9]
 * <p>
 * 输出: [1,3,9]
 * <p>
 * Created by caixi on 2022/8/1.
 */
public class LargestValues {

    /**
     * 还是层序遍历，想想模板和基本写法
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // 记住，层序遍历，使用queue来做的， 普通前、中、后序遍历，使用stack来做的
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 这层最大的数
            // 这一层有多少个元素
            List<Integer> max = new ArrayList<>();
            int len = queue.size();
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
