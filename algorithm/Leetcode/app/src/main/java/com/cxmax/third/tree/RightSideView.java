package com.cxmax.third.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/binary-tree-right-side-view/
 * <p>
 * 199. 二叉树的右视图
 * <p>
 * 输入: [1,2,3,null,5,null,4]
 * <p>
 * 输出: [1,3,4]
 * <p>
 * Created by caixi on 2022/7/31.
 */
public class RightSideView {

    /**
     * 遍历所有的右侧节点， 其实我理解， 就是按照前序遍历，遍历最后一个节点，在添加进去
     *
     * 1. 还是先写层序遍历的模板
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int len = queue.size();
            // 不一样的 用for循环遍历
            for (int i = 0; i < len; i++) {
                TreeNode tmp = queue.poll();
                if (tmp.left != null) {
                    queue.offer(tmp.left);
                }
                if (tmp.right != null) {
                    queue.offer(tmp.right);
                }
                if (i == len - 1) {
                    result.add(tmp.val);
                }
            }
        }
        return result;

    }

}
